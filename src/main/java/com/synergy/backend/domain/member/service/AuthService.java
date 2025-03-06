package com.synergy.backend.domain.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synergy.backend.domain.member.api.dto.SignupAdminRequestDto;
import com.synergy.backend.domain.member.api.dto.SignupAdminResponseDto;
import com.synergy.backend.domain.member.api.dto.SignupAttendeeRequestDto;
import com.synergy.backend.domain.member.api.dto.SignupAttendeeResponseDto;
import com.synergy.backend.domain.member.api.dto.SignupRecruiterRequestDto;
import com.synergy.backend.domain.member.api.dto.SignupRecruiterResponseDto;
import com.synergy.backend.domain.member.entity.Admin;
import com.synergy.backend.domain.member.entity.Attendee;
import com.synergy.backend.domain.member.entity.Recruiter;
import com.synergy.backend.domain.member.repository.AdminRepository;
import com.synergy.backend.domain.member.repository.AttendeeRepository;
import com.synergy.backend.domain.member.repository.RecruiterRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthService {

	private final AttendeeRepository attendeeRepository;
	private final AdminRepository adminRepository;
	private final RecruiterRepository recruiterRepository;
	private final PasswordEncoder passwordEncoder;

	public SignupAttendeeResponseDto registerAttendee(SignupAttendeeRequestDto request) {
		Attendee attendee = Attendee.of(
			request.email(),
			encodePassword(request.password()),
			request.name(),
			request.phone()
		);
		attendeeRepository.save(attendee);
		return SignupAttendeeResponseDto.from(attendee);
	}

	public SignupAdminResponseDto registerAdmin(SignupAdminRequestDto request) {
		Admin admin = Admin.of(
			request.name(),
			request.email(),
			encodePassword(request.password()),
			request.assignedAdminId()
		);
		adminRepository.save(admin);
		return SignupAdminResponseDto.from(admin);
	}

	public SignupRecruiterResponseDto registerRecruiter(SignupRecruiterRequestDto request) {
		Recruiter recruiter = Recruiter.of(
			request.name(),
			request.email(),
			encodePassword(request.password()),
			request.company(),
			request.responsibility()
		);
		recruiterRepository.save(recruiter);
		return SignupRecruiterResponseDto.from(recruiter);
	}

	private String encodePassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}
}
