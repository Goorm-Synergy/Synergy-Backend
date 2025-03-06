package com.synergy.backend.domain.member.entity;

import com.synergy.backend.global.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@MappedSuperclass
@SuperBuilder
public abstract class Member extends BaseEntity {

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, length = 25)
	private String name;

}
