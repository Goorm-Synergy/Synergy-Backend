package com.synergy.backend.domain.member.api.dto.response;

import com.synergy.backend.domain.member.entity.Attendee;

public record SignupAttendeeResponseDto(
	String name,
	String email
) {
	public static SignupAttendeeResponseDto from(Attendee attendee) {
		return new SignupAttendeeResponseDto(attendee.getName(), attendee.getEmail());
	}
}
