package com.synergy.backend.domain.session.controller;

import com.synergy.backend.domain.session.dto.SessionReqDto;
import com.synergy.backend.domain.session.service.SessionService;
import com.synergy.backend.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/session")
public class SessionController {

    private final SessionService sessionService;

    @PostMapping
    public ApiResponse createSession(@RequestBody SessionReqDto sessionReqDto) {
        sessionService.createSession(sessionReqDto);

        return ApiResponse.ok("Session created successfully!", 200);
    }

}
