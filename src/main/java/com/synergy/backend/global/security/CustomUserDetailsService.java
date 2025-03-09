package com.synergy.backend.global.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.synergy.backend.domain.member.entity.Member;
import com.synergy.backend.domain.member.entity.RoleType;
import com.synergy.backend.domain.member.repository.AdminRepository;
import com.synergy.backend.domain.member.repository.AttendeeRepository;
import com.synergy.backend.domain.member.repository.RecruiterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final AdminRepository adminRepository;
	private final AttendeeRepository attendeeRepository;
	private final RecruiterRepository recruiterRepository;

	/**
	 * Spring Security 기본 인증 방식 (JWT 없이 사용 가능)
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = findMemberByEmail(email);
		return new CustomUserDetails(member);
	}

	/**
	 * JWT 인증 시, Email + RoleType 기반으로 사용자 조회
	 */
	public UserDetails loadUserByEmailAndRole(String email, RoleType role) {
		return switch (role) {
			case ADMIN -> adminRepository.findByEmail(email)
				.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("Admin not found with email: " + email));
			case RECRUITER -> recruiterRepository.findByEmail(email)
				.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("Recruiter not found with email: " + email));
			case ATTENDEE -> attendeeRepository.findByEmail(email)
				.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("Attendee not found with email: " + email));
		};
	}

	/**
	 * 역할 정보 없이 Email만으로 `Member` 조회 (Spring Security의 기본 로그인)
	 */
	private Member findMemberByEmail(String email) {
		return adminRepository.findByEmail(email)
			.map(member -> (Member) member)
			.or(() -> recruiterRepository.findByEmail(email).map(member -> (Member) member))
			.or(() -> attendeeRepository.findByEmail(email).map(member -> (Member) member))
			.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
	}
}
