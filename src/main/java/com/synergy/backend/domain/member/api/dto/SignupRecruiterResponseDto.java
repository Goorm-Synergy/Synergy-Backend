package com.synergy.backend.domain.member.api.dto;

import com.synergy.backend.domain.member.entity.Recruiter;

public record SignupRecruiterResponseDto(
	String name,
	String email,
	String company
) {
	public static SignupRecruiterResponseDto from(Recruiter recruiter) {
		return new SignupRecruiterResponseDto(recruiter.getName(), recruiter.getEmail(), recruiter.getCompany());
	}
}
