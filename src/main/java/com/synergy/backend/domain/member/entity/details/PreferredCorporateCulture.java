package com.synergy.backend.domain.member.entity.details;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PreferredCorporateCulture {
	HORIZONTAL_COMMUNICATION("수평적 소통 문화"),
	CONTINUOUS_TECH_INNOVATION("지속적인 기술 혁신 추구"),
	COLLABORATION_AND_KNOWLEDGE_SHARING("협업과 지식 공유 중시"),
	FLEXIBLE_WORK_ENVIRONMENT("유연한 근무 환경"),
	PERFORMANCE_BASED_EVALUATION("성과 중심 평가");

	private final String description;
}
