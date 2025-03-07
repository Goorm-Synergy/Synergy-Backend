package com.synergy.backend.domain.member.dto;

import com.synergy.backend.domain.member.entity.PositionType;
import com.synergy.backend.domain.techstack.entity.MemberTechStack;

import java.util.Set;

public record AttendeeListResponseDto(
        String attendeeProfileImageUrl,
        PositionType position,
        String yearsOfExperience,
        Set<MemberTechStack> memberTechStacks,
        String name
) {
}
