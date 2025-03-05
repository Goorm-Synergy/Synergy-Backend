package com.synergy.backend.domain.member.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synergy.backend.domain.member.entity.Member;
import com.synergy.backend.domain.member.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserAuthService {
	private final AdminRepository memberRepository;

	public Optional<Member> findAuthUserByEmail(String email) {
		return memberRepository.findByEmail(email);
	}
}
