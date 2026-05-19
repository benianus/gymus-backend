package com.jetbrains.gymusserverjava.memberships;

import com.jetbrains.gymusserverjava.memberships.dtos.requests.RegisterMemberRequestDto;
import com.jetbrains.gymusserverjava.memberships.dtos.requests.UpdateMemberRequestDto;
import com.jetbrains.gymusserverjava.memberships.dtos.responses.MemberCardResponseDto;
import com.jetbrains.gymusserverjava.memberships.dtos.responses.MemberResponseDto;
import org.springframework.data.domain.Page;

public interface MembershipService {

    void registerMember(RegisterMemberRequestDto registerMemberRequestDto);

    void recordAttendance(int memberId);

    void renewMembership(int memberId);

    Page<MemberResponseDto> findAllMembers(int pageNumber, int pageSize);

    MemberCardResponseDto findMemberCard(int memberId);

    void deleteMember(int memberId);

    void updateMember(int memberId, UpdateMemberRequestDto updateMemberRequestDto);

}
