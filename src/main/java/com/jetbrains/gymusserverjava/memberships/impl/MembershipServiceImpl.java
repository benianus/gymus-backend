package com.jetbrains.gymusserverjava.memberships.impl;

import com.jetbrains.gymusserverjava.auth.repositories.UserRepository;
import com.jetbrains.gymusserverjava.memberships.MembershipService;
import com.jetbrains.gymusserverjava.memberships.dtos.requests.RegisterMemberRequestDto;
import com.jetbrains.gymusserverjava.memberships.dtos.requests.UpdateMemberRequestDto;
import com.jetbrains.gymusserverjava.memberships.dtos.responses.MemberCardResponseDto;
import com.jetbrains.gymusserverjava.memberships.dtos.responses.MemberResponseDto;
import com.jetbrains.gymusserverjava.memberships.entities.MemberAttendance;
import com.jetbrains.gymusserverjava.memberships.entities.MembershipRenewal;
import com.jetbrains.gymusserverjava.memberships.mappers.MemberCardMapper;
import com.jetbrains.gymusserverjava.memberships.mappers.MemberDocumentMapper;
import com.jetbrains.gymusserverjava.memberships.mappers.MemberMapper;
import com.jetbrains.gymusserverjava.memberships.mappers.MembershipMapper;
import com.jetbrains.gymusserverjava.memberships.repositories.*;
import com.jetbrains.shared.exceptions.CustomExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private MemberAttendanceRepository memberAttendanceRepository;

    @Autowired
    private MemberCardRepository memberCardRepository;

    @Autowired
    private MemberDocumentRepository memberDocumentRepository;

    @Autowired
    private MembershipRenewalRepository membershipRenewalRepository;

    @Autowired
    private MembershipTypeRepository membershipTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MemberDocumentMapper memberDocumentMapper;

    @Autowired
    private MemberCardMapper memberCardMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MembershipMapper membershipMapper;

    @Autowired
    private JdbcTemplate db;

    /**
     * {@summary if the member is older than 18 check if he attached parental authorization
     * * because the parental auth accept null}
     */
    private static void checkMemberAge(RegisterMemberRequestDto dto) {
        if(dto.age() < 18 && dto.parentalAuthorization() == null) {
            throw CustomExceptionHandler.ageExceed(
                    "member should be older than 18 or Parental authorization needed");
        }
    }

    @Override
    @Transactional
    public void registerMember(RegisterMemberRequestDto dto) {
        // check if the member is older than 18
        checkMemberAge(dto);

        // TODO: use user details later
        // var userDetails = Helpers.INSTANCE.getUserDetails();

        var user = userRepository.findOneByUsername("benianus")
                                 .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                         "username not found"
                                 ));
        // member
        var member = memberRepository.save(memberMapper.toEntity(dto, user));

        /*
         * member document
         */

        var memberDocument = memberDocumentRepository.save(memberDocumentMapper.toEntity(
                dto,
                member,
                user
        ));

        // membership type
        var membershipType = membershipTypeRepository.findMembershipTypeByName(dto.membershipType())
                                                     .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                                             "membership type not found"
                                                     ));

        // membership
        var membership = membershipRepository.save(membershipMapper.toEntity(
                member,
                membershipType,
                user
        ));

        // membership card
        var membershipCard = memberCardRepository.save(memberCardMapper.toEntity(
                member,
                membership,
                user
        ));
    }

    @Override
    @Transactional
    //    @PreAuthorize("@securityUtils.isOwner(#memberid)")
    public void recordAttendance(int memberId) {
        var isChecked = memberAttendanceRepository.isMemberChecked(memberId);

        if(isChecked) {
            throw CustomExceptionHandler.attendanceAlreadyChecked(
                    "member already already checked in today");
        }

        var member = memberRepository.findById(memberId)
                                     .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                             "member not found"));

        var user = userRepository.findOneByUsername("benianus")
                                 .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                         "user not found"));

        var newMemberAttendance = new MemberAttendance();

        newMemberAttendance.setMember(member);
        newMemberAttendance.setUser(user);

        memberAttendanceRepository.save(newMemberAttendance);
    }

    @Override
    //    @PreAuthorize("@securityUtils.isOwner(#memberId)")
    public void renewMembership(int memberId) {
        var membershipFounded = membershipRepository.findByMemberId(memberId)
                                                    .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                                            "membership not found"));

        if(membershipFounded.getEndDate().isAfter(LocalDate.now())) {
            throw CustomExceptionHandler.membershipAlreadyActive("membership already active");
        }

        // renew membership in the memberships table
        membershipFounded.setStartDate(LocalDate.now());
        membershipFounded.setEndDate(LocalDate.now().plusDays(30));
        membershipRepository.save(membershipFounded);

        // register the renewal in renewal memberships table
        var membershipRenewal = new MembershipRenewal();

        var member = memberRepository.findById(memberId)
                                     .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                             "member not found"));

        // // TODO: get the user from the user logged in later
        var user = userRepository.findOneByUsername("benianus")
                                 .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                         "user not found"));

        membershipRenewal.setMember(member);
        membershipRenewal.setUser(user);

        membershipRenewalRepository.save(membershipRenewal);
    }

    @Override
    public Page<MemberResponseDto> findAllMembers(int pageNumber, int pageSize) {
        var zeroBasedPageNumber = pageNumber - 1;
        var pageable = PageRequest.of(zeroBasedPageNumber, pageSize);
        return memberRepository.findAllMembers(pageable);
    }

    @Override
    public MemberCardResponseDto findMemberCard(int memberId) {
        return memberCardRepository.findMemberCard(memberId)
                                   .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                           "memberCard not found"));
    }

    @Override
    @Transactional
    public void deleteMember(int memberId) {
        var membership = membershipRepository.findOneByMemberId(memberId)
                                             .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                                     "membership not found"));
        var memberDocument = memberDocumentRepository.findOneByMemberId(memberId)
                                                     .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                                             "member document not found"));
        var memberCard = memberCardRepository.findOneByMemberId(memberId)
                                             .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                                     "member card not found"));
        var memberAttendances = memberAttendanceRepository.findAllByMemberId(memberId);
        var memberRenewals = membershipRenewalRepository.findAllByMemberId(memberId);
        var member = memberRepository.findById(memberId)
                                     .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                             "member not found"));

        membershipRepository.delete(membership);
        memberCardRepository.delete(memberCard);
        memberDocumentRepository.delete(memberDocument);
        memberAttendanceRepository.deleteAll(memberAttendances);
        membershipRenewalRepository.deleteAll(memberRenewals);
        memberRepository.delete(member);
    }

    @Override
    public void updateMember(int memberId, UpdateMemberRequestDto updateMemberRequestDto) {
        var member = memberRepository.findById(memberId)
                                     .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                             "member not found"));

        // set the information that you want to update
        memberRepository.save(member);
    }

}
