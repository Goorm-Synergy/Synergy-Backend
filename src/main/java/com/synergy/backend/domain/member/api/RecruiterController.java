package com.synergy.backend.domain.member.api;

import com.synergy.backend.domain.member.api.dto.AttendeeDetailResponseDto;
import com.synergy.backend.domain.member.api.dto.AttendeeListResponseDto;
import com.synergy.backend.domain.member.entity.RoleType;
import com.synergy.backend.domain.member.exception.AccessDeniedException;
import com.synergy.backend.domain.member.service.RecruiterService;
import com.synergy.backend.global.common.ApiResponse;
import com.synergy.backend.global.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/attendee")
@RequiredArgsConstructor
public class RecruiterController {

    private final RecruiterService recruiterService;

    @GetMapping("/{id}")
    public ApiResponse<AttendeeDetailResponseDto> findAttendee(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                               @PathVariable("id") Long attendeeId) {
        String identifier = userDetails.getIdentifier();
        RoleType role = userDetails.getRole();
        if(role == RoleType.ATTENDEE) {
            throw new AccessDeniedException();
        }
        return ApiResponse.ok(recruiterService.findAttendeeFrom(identifier, attendeeId), 200);
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
