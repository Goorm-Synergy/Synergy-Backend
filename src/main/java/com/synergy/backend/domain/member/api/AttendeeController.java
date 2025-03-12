package com.synergy.backend.domain.member.api;

import java.util.Set;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synergy.backend.domain.interest.entity.Interest;
import com.synergy.backend.domain.member.api.dto.request.InterestRequestDto;
import com.synergy.backend.domain.member.entity.Attendee;
import com.synergy.backend.domain.member.entity.RoleType;
import com.synergy.backend.domain.member.exception.AccessDeniedException;
import com.synergy.backend.domain.member.repository.AttendeeRepository;
import com.synergy.backend.domain.member.service.AttendeeService;
import com.synergy.backend.global.common.ApiResponse;
import com.synergy.backend.global.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/interest")
@RequiredArgsConstructor
public class AttendeeController {

	private final AttendeeService attendeeService;
	private final AttendeeRepository attendeeRepository;

	@PatchMapping
	public ApiResponse<?> addUserInterest(
		@AuthenticationPrincipal CustomUserDetails userDetails,
		@RequestBody InterestRequestDto request) {

		String username = userDetails.getIdentifier();
		RoleType role = userDetails.getRole();

		if (role != RoleType.ATTENDEE) {
			throw new AccessDeniedException();
		}

		Attendee attendee = attendeeRepository.findByEmail(username)
			.orElseThrow(() -> new IllegalArgumentException("Attendee not found with email: " + username));


		Set<Interest> interests = attendeeService.addInterests(attendee, request.interestIds());

		return ApiResponse.ok(interests.toString(), 200);
	}
}
