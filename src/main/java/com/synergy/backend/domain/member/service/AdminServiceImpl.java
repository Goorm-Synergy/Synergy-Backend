package com.synergy.backend.domain.member.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synergy.backend.domain.member.api.dto.resposne.AttendeeLevelRankingResponseDto;
import com.synergy.backend.domain.member.api.dto.resposne.AttendeePointRankingResponseDto;
import com.synergy.backend.domain.member.entity.Attendee;
import com.synergy.backend.domain.member.entity.details.MembershipLevelType;
import com.synergy.backend.domain.member.repository.AttendeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

	private final AttendeeRepository attendeeRepository;

	@Transactional(readOnly = true)
	@Override
	public Page<AttendeeLevelRankingResponseDto> getAttendeeLevelRankings(MembershipLevelType membershipLevel,
		Pageable pageable) {
		Page<Attendee> attendees = (membershipLevel != null)
			? attendeeRepository.findByMembershipLevelTypeOrderByTotalPointsDesc(membershipLevel, pageable)
			: attendeeRepository.findAllByOrderByTotalPointsDesc(pageable);

		return attendees.map(AttendeeLevelRankingResponseDto::from);
	}

	@Transactional(readOnly = true)
	@Override
	public Page<AttendeePointRankingResponseDto> getAttendeePointRankings(Pageable pageable) {
		Page<Attendee> attendees = attendeeRepository.findAllByOrderByTotalPointsDesc(pageable);
		return attendees.map(AttendeePointRankingResponseDto::from);
	}
}
