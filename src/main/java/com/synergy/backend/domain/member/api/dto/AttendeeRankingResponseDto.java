package com.synergy.backend.domain.member.api.dto;

import com.synergy.backend.domain.member.entity.Attendee;

public record AttendeeRankingResponseDto(
	String membershipLevel,
	String attendeeName,
	Integer totalPoints,
	Long userId
) {
	public static AttendeeRankingResponseDto from(Attendee attendee) {
		return new AttendeeRankingResponseDto(
			attendee.getMembershipLevelType().name(),
			attendee.getName(),
			attendee.getTotalPoints(),
			attendee.getId()
		);
	}
}
