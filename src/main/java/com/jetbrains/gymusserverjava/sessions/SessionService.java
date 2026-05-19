package com.jetbrains.gymusserverjava.sessions;

import com.jetbrains.gymusserverjava.sessions.dtos.responses.SessionResponseDto;
import com.jetbrains.gymusserverjava.sessions.entities.Session;

import java.util.List;

public interface SessionService {

    List<SessionResponseDto> findAllSessions();

    void registerSession(Session session);

}
