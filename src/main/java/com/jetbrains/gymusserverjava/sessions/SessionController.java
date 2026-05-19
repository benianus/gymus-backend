package com.jetbrains.gymusserverjava.sessions;

import com.jetbrains.gymusserverjava.sessions.dtos.requests.RegisterSessionRequestDto;
import com.jetbrains.gymusserverjava.sessions.dtos.responses.SessionResponseDto;
import com.jetbrains.shared.dtos.ApiResponse;
import com.jetbrains.shared.dtos.PagedResponse;
import com.jetbrains.shared.utils.Helpers;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sessions")
public class SessionController {

    private final SessionService sessionService;
    private final Helpers helpers;

    public SessionController(SessionService sessionService, Helpers helpers) {
        this.sessionService = sessionService;
        this.helpers = helpers;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<List<SessionResponseDto>>>> getAllSessions(
            @RequestParam(name = "page", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        var sessions = sessionService.findAllSessions(pageNumber, pageSize);

        var pagedResponse = helpers.getPagedResponse(sessions);

        return new ResponseEntity<>(new ApiResponse<>(pagedResponse), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> registerSession(
            @RequestBody @Valid RegisterSessionRequestDto registerSessionRequestDto
    ) {
        sessionService.registerSession(registerSessionRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
