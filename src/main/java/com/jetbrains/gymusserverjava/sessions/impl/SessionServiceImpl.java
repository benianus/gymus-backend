package com.jetbrains.gymusserverjava.sessions.impl;

import com.jetbrains.gymusserverjava.auth.repositories.UserRepository;
import com.jetbrains.gymusserverjava.sessions.SessionMapper;
import com.jetbrains.gymusserverjava.sessions.SessionService;
import com.jetbrains.gymusserverjava.sessions.dtos.requests.RegisterSessionRequestDto;
import com.jetbrains.gymusserverjava.sessions.dtos.responses.SessionResponseDto;
import com.jetbrains.gymusserverjava.sessions.repositories.SessionRepository;
import com.jetbrains.gymusserverjava.sessions.repositories.SessionTypeRepository;
import com.jetbrains.shared.exceptions.CustomExceptionHandler;
import com.jetbrains.shared.utils.Helpers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessionServiceImpl implements SessionService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final SessionTypeRepository sessionTypeRepository;
    private final SessionMapper sessionMapper;
    private final Helpers helpers;

    public SessionServiceImpl(
            SessionRepository sessionRepository,
            SessionMapper sessionMapper,
            UserRepository userRepository,
            SessionTypeRepository sessionTypeRepository,
            Helpers helpers
    ) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
        this.userRepository = userRepository;
        this.sessionTypeRepository = sessionTypeRepository;
        this.helpers = helpers;
    }

    @Override
    public Page<SessionResponseDto> findAllSessions(int pageNumber, int pageSize) {
        var pageable = PageRequest.of(pageNumber - 1, pageSize);
        return sessionRepository.findAllSessions(pageable);
    }

    @Override
    @Transactional
    public void registerSession(RegisterSessionRequestDto registerSessionRequestDto) {
        var username = helpers.getUserDetails().getUsername();
        var user = userRepository.findByUsername(username)
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
