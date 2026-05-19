package com.jetbrains.gymusserverjava.memberships.mappers;

import com.jetbrains.gymusserverjava.auth.entities.User;
import com.jetbrains.gymusserverjava.memberships.entities.Member;
import com.jetbrains.gymusserverjava.memberships.entities.Membership;
import com.jetbrains.gymusserverjava.memberships.entities.MembershipType;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper {

    public Membership toEntity(
            Member member,
            MembershipType membershipType,
            User user
    ) {
        var entity = new Membership();

        entity.setMember(member);
        entity.setMembershipType(membershipType);
        entity.setUser(user);

        return entity;
    }

}
