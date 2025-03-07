package com.synergy.backend.domain.member.api;

import com.synergy.backend.domain.member.api.dto.AttendeeDetailResponseDto;
import com.synergy.backend.domain.member.api.dto.AttendeeListResponseDto;
import com.synergy.backend.domain.member.service.RecruiterService;
import com.synergy.backend.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/attendee")
@RequiredArgsConstructor
public class RecruiterController {

    private final RecruiterService recruiterService;

    @GetMapping("/{id}")
    public ApiResponse<AttendeeDetailResponseDto> findAttendee(@PathVariable("id") Long attendeeId) {
        return ApiResponse.ok(recruiterService.findAttendeeFrom(attendeeId), 200);
    }

    @GetMapping
    public ApiResponse<AttendeeListResponseDto> findAttendees(
            @RequestParam(required = false) String techStack,
            @RequestParam(required = false) String field,
            @RequestParam(required = false) String jobInterest
    ) {
        return ApiResponse.ok(recruiterService.findAttendees(techStack, field, jobInterest), 200);
    }

}
