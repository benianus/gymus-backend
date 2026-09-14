package com.jetbrains.gymusserverjava.memberships

import com.jetbrains.gymusserverjava.memberships.dtos.requests.RegisterMemberRequestDto
import com.jetbrains.gymusserverjava.memberships.dtos.requests.UpdateMemberRequestDto
import com.jetbrains.gymusserverjava.memberships.dtos.responses.MemberCardResponseDto
import com.jetbrains.gymusserverjava.memberships.dtos.responses.MemberResponseDto
import org.springframework.data.domain.Page

interface MembershipService {
    fun registerMember(registerMemberRequestDto: RegisterMemberRequestDto)
    fun recordAttendance(memberId: Int)
    fun renewMembership(memberId: Int)
    fun findAllMembers(pageNumber: Int, pageSize: Int, username: String?): Page<MemberResponseDto>
    fun findMemberCard(memberId: Int): MemberCardResponseDto
    fun deleteMember(memberId: Int)
    fun updateMember(memberId: Int, updateMemberRequestDto: UpdateMemberRequestDto)
}
