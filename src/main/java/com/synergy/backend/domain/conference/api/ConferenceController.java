package com.synergy.backend.domain.conference.api;

import com.synergy.backend.domain.conference.dto.requset.ConferenceCreateRequest;
import com.synergy.backend.domain.conference.service.ConferenceService;
import com.synergy.backend.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/conference")
public class ConferenceController {

    private final ConferenceService conferenceService;

    @PostMapping
    public ApiResponse<?> registerConference(ConferenceCreateRequest request){
        return ApiResponse.ok(conferenceService.registerConference(request), 201);
    }
}
