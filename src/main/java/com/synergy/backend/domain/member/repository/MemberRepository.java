package com.synergy.backend.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.synergy.backend.domain.member.entity.Member;

@NoRepositoryBean
public interface MemberRepository<T extends Member> extends JpaRepository<T, Long> {
	Optional<T> findByEmail(String email);
}
