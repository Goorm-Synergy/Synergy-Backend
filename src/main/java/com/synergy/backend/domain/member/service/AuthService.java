package com.synergy.backend.domain.member.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synergy.backend.domain.member.api.dto.LoginAdminRequestDto;
import com.synergy.backend.domain.member.api.dto.LoginRequestDto;
import com.synergy.backend.domain.member.api.dto.SignupAttendeeRequestDto;
import com.synergy.backend.domain.member.api.dto.SignupAttendeeResponseDto;
import com.synergy.backend.domain.member.api.dto.TokenResponseDto;
import com.synergy.backend.domain.member.entity.Attendee;
import com.synergy.backend.domain.member.entity.Member;
import com.synergy.backend.domain.member.entity.RoleType;
import com.synergy.backend.domain.member.exception.AdminOrRecruiterNotFoundException;
import com.synergy.backend.domain.member.repository.AdminRepository;
import com.synergy.backend.domain.member.repository.AttendeeRepository;
import com.synergy.backend.domain.member.repository.RecruiterRepository;
import com.synergy.backend.global.redis.RefreshTokenRepository;
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

	@Transactional
	public SignupAttendeeResponseDto registerAttendee(SignupAttendeeRequestDto request) {
		Attendee attendee = Attendee.of(request.email(), encodePassword(request.password()), request.name(),
			request.phone());
		attendeeRepository.save(attendee);
		return SignupAttendeeResponseDto.from(attendee);
	}

	@Transactional
	public TokenResponseDto loginAttendee(LoginRequestDto request, RoleType role) {
		Member member = findMemberByEmailAndRole(request.email(), role);

		if (!passwordEncoder.matches(request.password(), member.getPassword())) {
			throw new BadCredentialsException("Invalid credentials");
		}

		String accessToken = jwtProvider.generateToken(member.getEmail(), role, true);
		String refreshToken = jwtProvider.generateToken(member.getEmail(), role, false);

		refreshTokenRepository.save(member.getEmail(), refreshToken);

		return new TokenResponseDto(accessToken, refreshToken, role.name());
	}

	@Transactional
	public TokenResponseDto loginAdminOrRecruiter(LoginAdminRequestDto request) {
		Member member = findAdminOrRecruiterByAssignedAdminId(request.assignedAdminId());

		String accessToken = jwtProvider.generateToken(member.getEmail(), member.getRoleType(), true);
		String refreshToken = jwtProvider.generateToken(member.getEmail(), member.getRoleType(), false);

		refreshTokenRepository.save(member.getEmail(), refreshToken);

		return new TokenResponseDto(accessToken, refreshToken, member.getRoleType().name());
	}

	@Transactional
	public void logout(String email) {
		refreshTokenRepository.delete(email);
	}

	private Member findAdminOrRecruiterByAssignedAdminId(String assignedAdminId) {
		return adminRepository.findByAssignedAdminId(assignedAdminId)
			.map(admin -> (Member)admin)
			.orElseGet(() -> recruiterRepository.findByAssignedAdminId(assignedAdminId)
				.map(recruiter -> (Member)recruiter)
				.orElseThrow(AdminOrRecruiterNotFoundException::new));
	}

	private Member findMemberByEmailAndRole(String email, RoleType role) {
		return switch (role) {
			case ADMIN ->
				adminRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
			case RECRUITER -> recruiterRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Recruiter not found"));
			case ATTENDEE -> attendeeRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Attendee not found"));
		};
	}

	private String encodePassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}
}
