package com.synergy.backend.domain.session.controller;

import com.synergy.backend.domain.session.dto.SessionReqDto;
import com.synergy.backend.domain.session.service.SessionService;
import com.synergy.backend.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/session")
public class SessionController {

    private final SessionService sessionService;

    @PostMapping
    public ApiResponse createSession(@RequestParam Long conferenceId, @RequestBody SessionReqDto sessionReqDto) {
        sessionService.createSession(conferenceId, sessionReqDto);

        return ApiResponse.ok("Session created successfully!", 200);
    }

//    @GetMapping
//    public ApiResponse getSessions(@RequestParam Long conferenceId) {
//
//    }
//
//    @GetMapping
//    public ApiResponse getSession(@RequestParam Long sessionId) {
//
//    }

    @PatchMapping
    public ApiResponse updateSession(@RequestParam Long sessionId, @RequestBody SessionReqDto sessionReqDto) {
        sessionService.updateSession(sessionId, sessionReqDto);

        return ApiResponse.ok("Session updated successfully!", 200);
    }

    @DeleteMapping
    public ApiResponse deleteSession(@RequestParam Long sessionId) {
        sessionService.deleteSession(sessionId);

        return ApiResponse.ok("Session deleted successfully!", 200);
    }

}
