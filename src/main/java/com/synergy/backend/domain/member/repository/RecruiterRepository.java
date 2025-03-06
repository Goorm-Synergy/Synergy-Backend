package com.synergy.backend.domain.member.repository;

import java.util.Optional;

import com.synergy.backend.domain.member.entity.Recruiter;


public interface RecruiterRepository extends MemberRepository<Recruiter> {
	@Override
	Optional<Recruiter> findByEmail(String email);
}
