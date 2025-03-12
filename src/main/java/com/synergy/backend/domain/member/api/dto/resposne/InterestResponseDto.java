package com.synergy.backend.domain.member.api.dto.resposne;

import java.util.List;

import com.synergy.backend.domain.member.entity.Attendee;

public record InterestResponseDto(String email,
								  String name,
								  List<String> interests) {
	public static InterestResponseDto from(Attendee attendee) {
		return new InterestResponseDto(
			attendee.getEmail(),
			attendee.getName(),
			attendee.getMemberInterests().stream()
				.map(interest -> interest.getInterest().getName())
				.toList()
		);
	}
}
