package com.synergy.backend.domain.member.service;

import com.synergy.backend.domain.member.api.dto.AttendeeDetailResponseDto;
import com.synergy.backend.domain.member.api.dto.AttendeeListResponseDto;
import com.synergy.backend.domain.member.repository.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecruiterServiceImpl implements RecruiterService {

    private final RecruiterRepository recruiterRepository;

    @Override
    public AttendeeDetailResponseDto findAttendeeFrom(Long id) {
        return null;
    }

    @Override
    public AttendeeListResponseDto findAttendees(String techStack, String field, String jobInterest) {
        return null;
    }
}
