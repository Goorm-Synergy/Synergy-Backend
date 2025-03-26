package com.synergy.backend.domain.meta.enums;

import java.util.Arrays;
import java.util.List;

import com.synergy.backend.domain.member.entity.details.AgeGroup;
import com.synergy.backend.domain.member.entity.details.BaseAttendeeDetailEnum;
import com.synergy.backend.domain.member.entity.details.ConferenceParticipationPurpose;
import com.synergy.backend.domain.member.entity.details.EducationLevelType;
import com.synergy.backend.domain.member.entity.details.ExperienceLevelType;
import com.synergy.backend.domain.member.entity.details.PreferredCorporateCulture;
import com.synergy.backend.domain.member.entity.details.RegionType;
import com.synergy.backend.domain.member.entity.details.WorkplaceSelectionFactor;

public enum EnumType {
	지역(RegionType.class),
	연령대(AgeGroup.class),
	컨퍼런스_참여_목적(ConferenceParticipationPurpose.class),
	직장_선택_요소(WorkplaceSelectionFactor.class),
	선호하는_기업_문화(PreferredCorporateCulture.class),
	경력(ExperienceLevelType.class),
	학력(EducationLevelType.class);;

	private final Class<? extends
		BaseAttendeeDetailEnum> enumClass;

	EnumType(Class<? extends BaseAttendeeDetailEnum> enumClass) {
		this.enumClass = enumClass;
	}

	public List<EnumResponseDto> getValues() {
		return Arrays.stream(enumClass.getEnumConstants())
			.map(EnumResponseDto::from)
			.toList();
	}
}
