package com.synergy.backend.domain.point.entity;

import com.synergy.backend.domain.member.entity.MembershipLevelType;
import com.synergy.backend.global.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String content;

	@Enumerated(EnumType.STRING)
	private MembershipLevelType membershipLevelType;

	@Enumerated(EnumType.STRING)
	private PointType pointType;

	private Long boothId;  // 부스 방문 시 부스 ID 저장
	private Long sessionId; // 세션 참여 시 세션 ID 저장
	private Long sessionQnAId; // 세션 Q&A 참여 시 세션 Q&A ID 저장
	private Long recruiterId; // 채용 담당자 미팅 시 담당자 ID 저장
	// private Long surveyId; // 설문조사 참여 시 설문 ID 저장
	// private String sharedContentUrl; // 컨텐츠 공유 시 공유한 URL 저장

	@Builder
	public Point(PointType pointType, Long boothId, Long sessionId, Long recruiterId) {
		this.pointType = pointType;
		this.boothId = boothId;
		this.sessionId = sessionId;
		this.recruiterId = recruiterId;
		this.content = generateContent(pointType, boothId, sessionId, recruiterId);
		this.membershipLevelType = MembershipLevelType.BRONZE; // 기본값
	}

	// 포인트 지급 사유 생성
	private String generateContent(PointType pointType, Long boothId, Long sessionId, Long recruiterId) {
		switch (pointType) {
			case SIGN_UP:
				return "회원가입 포인트 적립";
			case BOOTH_VISIT:
				return "부스 방문 포인트 적립 (부스 ID: " + boothId + ")";
			case SESSION_ATTEND:
				return "세션 참여 포인트 적립 (세션 ID: " + sessionId + ")";
			case SURVEY_PARTICIPATION:
				return "설문조사 참여 포인트 적립";
			case CONTENT_SHARE:
				return "컨텐츠 공유 포인트 적립";
			case RECRUITER_MEETING:
				return "채용 담당자 미팅 포인트 적립 (담당자 ID: " + recruiterId + ")";
			default:
				return "포인트 적립";
		}
	}
}
