package com.synergy.backend.domain.member.api.dto.request;

import java.util.Set;

import jakarta.validation.constraints.NotNull;

public record JobInfoDetailsRequestDto(
	@NotNull
	Integer desiredOccupationCode, // 희망 직무 (코드값)

	@NotNull
	Integer educationLevelCode, // 학력 (코드값)

	@NotNull
	Integer ageGroupCode, // 연령대 (코드값)

	@NotNull
	Set<Integer> skillCodes, // 보유 기술 (코드값 리스트)

	@NotNull
	Integer experienceLevelCode, // 경력 (코드값)

	@NotNull
	Set<Integer> preferredRegionCodes, // 희망 근무 지역 (코드값 리스트)

	String selfIntroduction, // 자기소개서
	String profileImageUrl, // 증명사진 (파일 업로드 시 URL 저장)
	String additionalInfo, // 경험 및 기타 정보
	Integer workplaceSelectionFactorCode, // 직장 선택 요소 (코드값)
	Integer preferredCorporateCultureCode, // 선호하는 기업 문화 (코드값)
	Integer conferencePurposeCode // 컨퍼런스 참여 목적 (코드값)
) {
}
