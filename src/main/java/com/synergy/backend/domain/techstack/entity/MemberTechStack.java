package com.synergy.backend.domain.techstack.entity;

import com.synergy.backend.domain.member.entity.Attendee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class MemberTechStack {

	@Id
	@Column(name = "member_tech_stack_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Attendee attendee;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tech_stack_id", nullable = false)
	private TechStack techStack;
}
