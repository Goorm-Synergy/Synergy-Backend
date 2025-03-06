package com.synergy.backend.domain.member.repository;

import java.util.Optional;

import com.synergy.backend.domain.member.entity.Admin;

public interface AdminRepository extends MemberRepository<Admin> {

	@Override
	Optional<Admin> findByEmail(String email);
}
