package com.jetbrains.gymusserverjava.memberships.mappers;

import com.jetbrains.gymusserverjava.auth.entities.User;
import com.jetbrains.gymusserverjava.memberships.dtos.requests.RegisterMemberRequestDto;
import com.jetbrains.gymusserverjava.memberships.entities.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member toEntity(RegisterMemberRequestDto dto, User user) {
        var entity = new Member();

        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setEmail(dto.email());
        entity.setPhoneNumber(dto.phoneNumber());
        entity.setAddress(dto.address());
        entity.setBirthdate(dto.birthdate());
        entity.setUser(user);

        return entity;
    }


}
