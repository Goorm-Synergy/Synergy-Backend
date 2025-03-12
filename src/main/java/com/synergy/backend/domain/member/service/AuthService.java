package com.synergy.backend.domain.member.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synergy.backend.domain.member.api.dto.request.LoginAttendeeRequestDto;
import com.synergy.backend.domain.member.api.dto.request.SignupAttendeeRequestDto;
import com.synergy.backend.domain.member.api.dto.resposne.SignupAttendeeResponseDto;
import com.synergy.backend.domain.member.api.dto.resposne.TokenResponseDto;
import com.synergy.backend.domain.member.entity.Admin;
import com.synergy.backend.domain.member.entity.Attendee;
import com.synergy.backend.domain.member.entity.Recruiter;
import com.synergy.backend.domain.member.entity.User;
import com.synergy.backend.domain.member.exception.NotFoundMember;
import com.synergy.backend.domain.member.repository.AdminRepository;
import com.synergy.backend.domain.member.repository.AttendeeRepository;
import com.synergy.backend.domain.member.repository.RecruiterRepository;
import com.synergy.backend.global.redis.RefreshTokenRepository;
import com.synergy.backend.global.security.CustomUserDetails;
import com.synergy.backend.global.security.JwtProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

	private final AttendeeRepository attendeeRepository;
	private final AdminRepository adminRepository;
	private final RecruiterRepository recruiterRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;
	private final RefreshTokenRepository refreshTokenRepository;
	private final AuthenticationManager authenticationManager;

	@Transactional
	public SignupAttendeeResponseDto registerAttendee(SignupAttendeeRequestDto request) {
		Attendee attendee = Attendee.of(request.email(), encodePassword(request.password()), request.name(),
			request.phone());

		attendeeRepository.save(attendee);
		return SignupAttendeeResponseDto.from(attendee);
	}

	// 이메일+비밀번호 검증 후 jwt 발급
	@Transactional
	public TokenResponseDto loginAsAttendee(String email, String rawPassword) {
		Attendee attendee = attendeeRepository.findByEmail(email)
			.orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));

		if (!passwordEncoder.matches(rawPassword, attendee.getPassword())) {
			throw new IllegalArgumentException("Invalid password");
		}

		return jwtProvider.generateToken(new CustomUserDetails(attendee));
	}

	@Transactional
	public TokenResponseDto loginAsAdminOrRecruiter(String authCode) {
		// 먼저 관리자(Admin) 조회
		Optional<Admin> adminOpt = adminRepository.findByAdminAuthCode(authCode);
		if (adminOpt.isPresent()) {
			Admin admin = adminOpt.get();
			return jwtProvider.generateToken(new CustomUserDetails(admin));
		}

		// 관리자 정보가 없으면 채용 담당자(Recruiter) 조회
		Optional<Recruiter> recruiterOpt = recruiterRepository.findByRecruiterAuthCode(authCode);
		if (recruiterOpt.isPresent()) {
			Recruiter recruiter = recruiterOpt.get();
			return jwtProvider.generateToken(new CustomUserDetails(recruiter));
		}

		// 두 곳에서 모두 찾지 못하면 예외 발생
		throw new IllegalArgumentException("Invalid authCode");
	}

	@Transactional(readOnly = true)
	private Attendee findAttendee(LoginAttendeeRequestDto request) {
		return attendeeRepository.findByEmail(request.email()).orElseThrow(NotFoundMember::new);
	}

	@Transactional
	public void logout(String email) {
		refreshTokenRepository.delete(email);
	}

	// 로그인된 유저객체 가져오기
	@Transactional(readOnly = true)
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
			throw new IllegalStateException("인증된 사용자가 없습니다.");
		}
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		String identifier = userDetails.getUsername(); // username 혹은 필요한 식별자

		return attendeeRepository.findByEmail(identifier)
			.map(attendee -> (User) attendee)
			.or(() -> adminRepository.findByAdminAuthCode(identifier)
				.map(admin -> (User) admin))
			.or(() -> recruiterRepository.findByRecruiterAuthCode(identifier)
				.map(recruiter -> (User) recruiter))
			.orElseThrow(() -> new IllegalArgumentException("User not found with identifier: " + identifier));
	}

	private String encodePassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}
}
