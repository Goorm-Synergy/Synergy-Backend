package com.synergy.backend.domain.member.service;

import java.util.List;

import com.synergy.backend.domain.member.api.dto.resposne.LikedAttendeeResponseDto;

public interface RecruiterLikeService {
	void likeAttendee(Long id, Long attendeeId);

	void unlikeAttendee(Long id, Long attendeeId);

	List<LikedAttendeeResponseDto> getLikedAttendees(Long id);
}
