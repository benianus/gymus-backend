package com.jetbrains.gymusserverjava.sessions;

import com.jetbrains.gymusserverjava.sessions.dtos.requests.RegisterSessionRequestDto;
import com.jetbrains.gymusserverjava.sessions.dtos.responses.SessionResponseDto;
import org.springframework.data.domain.Page;

public interface SessionService {

    Page<SessionResponseDto> findAllSessions(int pageNumber, int pageSize);

    void registerSession(RegisterSessionRequestDto session);

}
