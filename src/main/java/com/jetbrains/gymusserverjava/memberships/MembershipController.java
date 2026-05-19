package com.jetbrains.gymusserverjava.memberships;

import com.jetbrains.gymusserverjava.memberships.dtos.requests.RegisterMemberRequestDto;
import com.jetbrains.gymusserverjava.memberships.dtos.responses.MemberCardResponseDto;
import com.jetbrains.gymusserverjava.memberships.dtos.responses.MemberResponseDto;
import com.jetbrains.shared.dtos.ApiResponse;
import com.jetbrains.shared.dtos.PagedResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/memberships")
public class MembershipController {

    private final MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @GetMapping("members")
    public ResponseEntity<ApiResponse<PagedResponse<List<MemberResponseDto>>>> findAllMembers(
            @RequestParam(name = "page", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        var members = membershipService.findAllMembers(pageNumber, pageSize);

        var pagedResponse = new PagedResponse<>(
                members.getContent(),
                pageNumber,
                members.getSize(),
                members.getTotalElements(),
                members.getTotalPages(),
                members.hasNext(),
                members.hasPrevious()
        );

        return new ResponseEntity<>(new ApiResponse<>(pagedResponse), HttpStatus.OK);
    }

    @GetMapping("members/{memberId}")
    ResponseEntity<ApiResponse<MemberCardResponseDto>> findMemberCard(@PathVariable int memberId) {
        var memberCard = membershipService.findMemberCard(memberId);
        return ResponseEntity.ok().body(new ApiResponse<>(memberCard));
    }

    @PostMapping("register")
    public ResponseEntity<ApiResponse<?>> registerMember(
            @ModelAttribute @Valid RegisterMemberRequestDto dto
    ) {
        membershipService.registerMember(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("record/{memberId}/attendance")
    public ResponseEntity<ApiResponse<?>> recordAttendance(@PathVariable int memberId) {
        membershipService.recordAttendance(memberId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("{memberId}/renew")
    public ResponseEntity<ApiResponse<?>> renewMembership(@PathVariable int memberId) {
        membershipService.renewMembership(memberId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("members/{memberId}")
    public ResponseEntity<ApiResponse<?>> updateMember(@PathVariable int memberId) {
        return null;
    }

    @DeleteMapping("members/{memberId}")
    public ResponseEntity<ApiResponse<?>> deleteMember(@PathVariable int memberId) {
        return null;
    }

}
