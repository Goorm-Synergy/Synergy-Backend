package com.synergy.backend.domain.member.api.dto;

import com.synergy.backend.domain.member.entity.Attendee;

public record SignupAttendeeResponseDto(
	String name,
	String phone
) {
	public static SignupAttendeeResponseDto from(Attendee attendee) {
		return new SignupAttendeeResponseDto(attendee.getName(), attendee.getPhone());
	}
}
