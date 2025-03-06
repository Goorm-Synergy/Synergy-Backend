package com.synergy.backend.domain.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synergy.backend.domain.member.api.dto.SignupAdminRequestDto;
import com.synergy.backend.domain.member.api.dto.SignupAttendeeRequestDto;
import com.synergy.backend.domain.member.api.dto.SignupRecruiterRequestDto;
import com.synergy.backend.domain.member.repository.AdminRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

	// private final AdminRepository memberRepository;

	public void registerAttendee(SignupAttendeeRequestDto request) {

	}

	public void registerAdmin(SignupAdminRequestDto request) {
	}

	public void registerRecruiter(SignupRecruiterRequestDto request) {
	}
}
