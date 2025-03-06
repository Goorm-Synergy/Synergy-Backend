package com.synergy.backend.domain.member.api.dto;

import com.synergy.backend.domain.member.entity.RoleType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SignupRequestDto {

	@Email
	private String email;

	@NotEmpty
	private String name;

	@NotNull
	private RoleType roleType;

	@NotEmpty
	private String username;

	@NotEmpty
	private String password;
}
