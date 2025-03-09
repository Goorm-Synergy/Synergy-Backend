package com.synergy.backend.domain.member.api;

import java.util.Set;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synergy.backend.domain.interest.entity.Interest;
import com.synergy.backend.domain.member.api.dto.InterestRequestDto;
import com.synergy.backend.domain.member.entity.Attendee;
import com.synergy.backend.domain.member.entity.Member;
import com.synergy.backend.domain.member.entity.RoleType;
import com.synergy.backend.domain.member.exception.AccessDeniedException;
import com.synergy.backend.domain.member.service.AttendeeService;
import com.synergy.backend.global.common.ApiResponse;
import com.synergy.backend.global.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/interest")
@RequiredArgsConstructor
public class AttendeeController {

	private final AttendeeService attendeeService;

	@PatchMapping
	public ApiResponse<?> addUserInterest(
		@AuthenticationPrincipal CustomUserDetails<? extends Member> userDetails,
		@RequestBody InterestRequestDto request) {

		Member member = userDetails.getMember();

		if (member.getRoleType() != RoleType.ATTENDEE) {
			throw new AccessDeniedException();
		}

		Attendee attendee = (Attendee) member;

		Set<Interest> interests = attendeeService.addInterests(attendee, request.interestIds());

		return ApiResponse.ok(interests.toString(), 200);
	}
}
