package com.jetbrains.gymusserverjava.sessions.impl;

import com.jetbrains.gymusserverjava.auth.repositories.UserRepository;
import com.jetbrains.gymusserverjava.sessions.SessionMapper;
import com.jetbrains.gymusserverjava.sessions.SessionService;
import com.jetbrains.gymusserverjava.sessions.dtos.requests.RegisterSessionRequestDto;
import com.jetbrains.gymusserverjava.sessions.dtos.responses.SessionResponseDto;
import com.jetbrains.gymusserverjava.sessions.repositories.SessionRepository;
import com.jetbrains.gymusserverjava.sessions.repositories.SessionTypeRepository;
import com.jetbrains.shared.exceptions.CustomExceptionHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final SessionTypeRepository sessionTypeRepository;
    private final SessionMapper sessionMapper;

    public SessionServiceImpl(
            UserDetailsService userDetailsService,
            SessionRepository sessionRepository,
            SessionMapper sessionMapper,
            UserRepository userRepository,
            SessionTypeRepository sessionTypeRepository
    ) {
        this.userDetailsService = userDetailsService;
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
        this.userRepository = userRepository;
        this.sessionTypeRepository = sessionTypeRepository;
    }

    @Override
    public Page<SessionResponseDto> findAllSessions(int pageNumber, int pageSize) {
        var pageable = PageRequest.of(pageNumber - 1, pageSize);
        return sessionRepository.findAllSessions(pageable);
    }

    @Override
    public void registerSession(RegisterSessionRequestDto registerSessionRequestDto) {
        // TODO: get user from security context later
        var user = userRepository.findOneByUsername("benianus")
                                 .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                         "User not found"));

        var sessionType
                = sessionTypeRepository.findOneByName(registerSessionRequestDto.sessionTypeName())
                                       .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                               "Session Type not found"));

        sessionRepository.save(sessionMapper.toEntity(
                registerSessionRequestDto,
                user,
                sessionType
        ));
    }

}
