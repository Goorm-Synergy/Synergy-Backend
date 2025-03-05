package com.synergy.backend.domain.member.entity;

import static jakarta.persistence.FetchType.*;

import java.util.HashSet;
import java.util.Set;

import com.synergy.backend.domain.conference.model.Conference;
import com.synergy.backend.domain.interest.entity.MemberInterest;
import com.synergy.backend.domain.techstack.entity.MemberTechStack;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Attendee extends Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	// 현재 포인트 합계
	@Column(nullable = false)
	private int point = 0;

	// 등급
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private MembershipLevelType membershipLevelType;

	// 현재 직업
	@Enumerated(EnumType.STRING)
	private OccupationType occupationType;

	// 현재 직무
	@Enumerated(EnumType.STRING)
	private PositionType position;

	// 희망 직무
	@Enumerated(EnumType.STRING)
	private PositionType desiredPosition;

	// 경력
	private String yearsOfExperience;

	// 참가자-보유기술
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<MemberTechStack> memberTechStacks = new HashSet<>();

	// 자기소개서
	private String selfIntroduction;

	// 채용 희망여부
	private boolean isHiringInterested;

	// 경력 정보
	private String personalHistory;

	// 경험 및 기타 정보
	private String information;

	// 참가자-관심분야
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<MemberInterest> memberInterests = new HashSet<>();

	// 컨퍼런스
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "conference_id")
	private Conference conference;

}
