package com.synergy.backend.domain.member.service;

import com.synergy.backend.domain.member.api.dto.AttendeeDetailResponseDto;
import com.synergy.backend.domain.member.api.dto.AttendeeListResponseDto;

public interface RecruiterService {

    // 사용자 단일 정보 조회
    AttendeeDetailResponseDto findAttendeeFrom(String identifier, Long id);
    // 사용자 목록 정보 조회
    AttendeeListResponseDto findAttendees(String techStack, String field, String jobInterest);
}
