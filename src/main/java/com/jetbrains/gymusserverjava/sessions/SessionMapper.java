package com.jetbrains.gymusserverjava.sessions;

import com.jetbrains.gymusserverjava.auth.entities.User;
import com.jetbrains.gymusserverjava.sessions.dtos.requests.RegisterSessionRequestDto;
import com.jetbrains.gymusserverjava.sessions.entities.Session;
import com.jetbrains.gymusserverjava.sessions.entities.SessionType;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper {

    public Session toEntity(RegisterSessionRequestDto dto, User user, SessionType sessionType) {
        var session = new Session();

        session.setFullName(dto.fullName());
        session.setSessionType(sessionType);
        session.setUser(user);

        return session;
    }

}
