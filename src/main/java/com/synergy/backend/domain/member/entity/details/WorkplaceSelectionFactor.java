package com.synergy.backend.domain.member.entity.details;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WorkplaceSelectionFactor {
	GROWTH_AND_LEARNING_SUPPORT("성장 기회 및 학습 지원"),
	SALARY_AND_BENEFITS("연봉 및 복리후생"),
	WORK_LIFE_BALANCE("워라밸 (Work-Life Balance)"),
	PROJECT_SCALE_AND_CHALLENGE("프로젝트의 규모와 도전성"),
	COMPANY_STABILITY_AND_VISION("회사의 안정성 및 비전");

	private final String description;
}
