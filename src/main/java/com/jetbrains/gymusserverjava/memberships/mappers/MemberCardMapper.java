package com.jetbrains.gymusserverjava.memberships.mappers;

import com.jetbrains.gymusserverjava.auth.entities.User;
import com.jetbrains.gymusserverjava.memberships.entities.Member;
import com.jetbrains.gymusserverjava.memberships.entities.MemberCard;
import com.jetbrains.gymusserverjava.memberships.entities.Membership;
import org.springframework.stereotype.Component;

@Component
public class MemberCardMapper {

    public MemberCard toEntity(
            Member member,
            Membership membership,
            User user
    ) {
        var entity = new MemberCard();

        entity.setMember(member);
        entity.setMembership(membership);
        entity.setUser(user);

        return entity;
    }

}
