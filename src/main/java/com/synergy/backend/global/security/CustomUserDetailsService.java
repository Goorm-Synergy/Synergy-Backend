package com.synergy.backend.global.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.synergy.backend.domain.member.entity.Member;
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

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = findMemberByEmail(email);

		return User.withUsername(member.getEmail())
			.password(member.getPassword())
			.roles(member.getClass().getSimpleName()) // Attendee, Admin, Recruiter 중 하나
			.build();
	}

	private Member findMemberByEmail(String email) {
		return adminRepository.findByEmail(email)
			.map(member -> (Member)member)
			.or(() -> attendeeRepository.findByEmail(email).map(member -> (Member)member))
			.or(() -> recruiterRepository.findByEmail(email).map(member -> (Member)member))
			.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
	}
}
