package com.synergy.backend.domain.member.api.dto.resposne;

import java.util.Set;
import java.util.stream.Collectors;

import com.synergy.backend.domain.member.entity.Attendee;
import com.synergy.backend.domain.member.entity.details.BaseAttendeeDetailEnum;

public record AttendeeInfoDetailResponseDto(
	String name,
	String jobName,
	String experience,
	String education,
	String ageGroup,
	String techStacks,
	Set<String> desiredWorkRegion,
	String selfIntroduction,
	String information,
	Set<String> workplaceSelectionFactors,
	Set<String> preferredCorporateCultures
) {
	public static AttendeeInfoDetailResponseDto from(Attendee attendee) {
		return new AttendeeInfoDetailResponseDto(
			attendee.getName(),
			attendee.getCurrentJobCategory().getName(),
			attendee.getExperienceLevel().getDescription(),
			attendee.getEducationLevel().getDescription(),
			attendee.getAgeGroup().getDescription(),
			attendee.getTechStacks(),
			attendee.getDesiredWorkRegion()
				.stream().map(BaseAttendeeDetailEnum::getDescription)
				.collect(Collectors.toSet()),
			attendee.getSelfIntroduction(),
			attendee.getInformation(),
			attendee.getWorkplaceSelectionFactors()
				.stream().map(BaseAttendeeDetailEnum::getDescription)
				.collect(Collectors.toSet()),
			attendee.getPreferredCorporateCultures()
				.stream().map(BaseAttendeeDetailEnum::getDescription)
				.collect(Collectors.toSet())
		);
	}
}
