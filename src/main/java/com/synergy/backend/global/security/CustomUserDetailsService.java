package com.synergy.backend.global.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Attendee의 경우 email로 조회
		return attendeeRepository.findByEmail(username)
			.map(CustomUserDetails::new)
			.orElseGet(() ->
				adminRepository.findByAdminAuthCode(username)
					.map(CustomUserDetails::new)
					.orElseGet(() ->
						recruiterRepository.findByRecruiterAuthCode(username)
							.map(CustomUserDetails::new)
							.orElseThrow(() ->
								new UsernameNotFoundException("User not found with identifier: " + username)
							)
					)
			);
	}

}
