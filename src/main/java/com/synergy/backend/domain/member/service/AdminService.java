package com.synergy.backend.domain.member.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.synergy.backend.domain.member.api.dto.AttendeeRankingResponseDto;

public interface AdminService {
	Page<AttendeeRankingResponseDto> getAttendeeRankings(String grade, Pageable pageable);

}
