package com.synergy.backend.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
public class Admin extends Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Column
	private String assignedAdminId;

	public static Admin of(String name, String email, String encodedPassword, String assignedAdminId) {
		return Admin.builder()
			.email(email)
			.password(encodedPassword)
			.name(name)
			.assignedAdminId(assignedAdminId)
			.build();
	}
}
