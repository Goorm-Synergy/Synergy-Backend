package com.synergy.backend.domain.member.repository;

import java.util.Optional;

import com.synergy.backend.domain.member.entity.Attendee;


public interface AttendeeRepository extends MemberRepository<Attendee> {
	@Override
	Optional<Attendee> findByEmail(String email);
}
