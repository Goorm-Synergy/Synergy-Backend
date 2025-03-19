package com.synergy.backend.domain.member.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.synergy.backend.domain.member.api.dto.resposne.AttendeeLevelRankingResponseDto;
import com.synergy.backend.domain.member.api.dto.resposne.AttendeePointRankingResponseDto;

public interface AdminService {
	Page<AttendeeLevelRankingResponseDto> getAttendeeLevelRankings(String grade, Pageable pageable);

	Page<AttendeePointRankingResponseDto> getAttendeePointRankings(Pageable pageable);
}
