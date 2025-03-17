package com.synergy.backend.domain.member.api.dto;

import com.synergy.backend.domain.member.entity.Attendee;
import com.synergy.backend.domain.member.entity.OccupationType;
import com.synergy.backend.domain.member.entity.PositionType;
import lombok.Builder;

import java.util.Set;

public record AttendeeDetailResponseDto(
        String attendeeProfileImageUrl,
        OccupationType occupationType,
        PositionType position,
        PositionType desiredPosition,
        String yearsOfExperience,
        Set<String> techStacks,
        String selfIntroduction,
        String personalHistory,
        String information,
        String email,
        String name,
        String phone
) {
    @Builder
    public AttendeeDetailResponseDto(String attendeeProfileImageUrl, OccupationType occupationType, PositionType position, PositionType desiredPosition,
                                     String yearsOfExperience, Set<String> techStacks, String selfIntroduction, String personalHistory,
                                     String information, String email, String name, String phone) {
        this.attendeeProfileImageUrl = attendeeProfileImageUrl;
        this.occupationType = occupationType;
        this.position = position;
        this.desiredPosition = desiredPosition;
        this.yearsOfExperience = yearsOfExperience;
        this.techStacks = techStacks;
        this.selfIntroduction = selfIntroduction;
        this.personalHistory = personalHistory;
        this.information = information;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public static AttendeeDetailResponseDto of(Attendee attendee, Set<String> techStacks) {
        return AttendeeDetailResponseDto.builder()
                .attendeeProfileImageUrl(null)
                .occupationType(attendee.getOccupationType())
                .position(attendee.getPosition())
                .desiredPosition(attendee.getDesiredPosition())
                .yearsOfExperience(attendee.getYearsOfExperience())
                .techStacks(techStacks)
                .personalHistory(attendee.getPersonalHistory())
                .information(attendee.getInformation())
                .email(attendee.getEmail())
                .name(attendee.getName())
                .phone(attendee.getPhone())
                .build();
    }
}
