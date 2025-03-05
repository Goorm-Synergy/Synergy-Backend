package com.synergy.backend.domain.techstack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TechStack {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tech_stack_id")
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;
}
