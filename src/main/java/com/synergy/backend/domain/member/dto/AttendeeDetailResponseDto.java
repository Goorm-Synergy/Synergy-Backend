package com.synergy.backend.domain.member.dto;

import com.synergy.backend.domain.member.entity.OccupationType;
import com.synergy.backend.domain.member.entity.PositionType;
import com.synergy.backend.domain.techstack.entity.MemberTechStack;

import java.util.Set;

public record AttendeeDetailResponseDto(
        String attendeeProfileImageUrl,
        OccupationType occupationType,
        PositionType position,
        PositionType desiredPosition,
        String yearsOfExperience,
        Set<MemberTechStack> memberTechStacks,
        String selfIntroduction,
        String personalHistory,
        String information,
        String email,
        String name,
        String phone
) {
}
