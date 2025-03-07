package com.synergy.backend.domain.member.service;

import com.synergy.backend.domain.member.dto.AttendeeDetailResponseDto;
import com.synergy.backend.domain.member.dto.AttendeeListResponseDto;
import com.synergy.backend.domain.member.entity.Attendee;

public interface RecruiterService {

    // 사용자 단일 정보 조회
    AttendeeDetailResponseDto findAttendee(Long id);
    // 사용자 목록 정보 조회
    AttendeeListResponseDto findAttendees(String techStack, String field, String jobInterest);
}
