package com.synergy.backend.domain.member.service;

import com.synergy.backend.domain.member.api.dto.AttendeeDetailResponseDto;
import com.synergy.backend.domain.member.api.dto.AttendeeListResponseDto;
import com.synergy.backend.domain.member.entity.Attendee;
import com.synergy.backend.domain.member.exception.ForbiddenHiringUserException;
import com.synergy.backend.domain.member.exception.NotFoundUserException;
import com.synergy.backend.domain.member.repository.AttendeeRepository;
import com.synergy.backend.domain.member.repository.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecruiterServiceImpl implements RecruiterService {

    private final RecruiterRepository recruiterRepository;
    private final AttendeeRepository attendeeRepository;


    @Override
    public AttendeeDetailResponseDto findAttendeeFrom(String identifier, Long id) {
        Attendee attendee = attendeeRepository.findById(id).orElseThrow(NotFoundUserException::new);
        if (!attendee.isHiringInterested()) {
            throw new ForbiddenHiringUserException();
        }

        return AttendeeDetailResponseDto.of(attendee);
    }

    @Override
    public AttendeeListResponseDto findAttendees(String techStack, String field, String jobInterest) {
        return null;
    }
}
