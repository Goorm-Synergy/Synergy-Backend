package com.synergy.backend.domain.member.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synergy.backend.domain.interest.entity.Interest;
import com.synergy.backend.domain.interest.entity.MemberInterest;
import com.synergy.backend.domain.interest.repository.InterestRepository;
import com.synergy.backend.domain.interest.repository.MemberInterestRepository;
import com.synergy.backend.domain.member.entity.Attendee;
import com.synergy.backend.domain.member.repository.AttendeeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendeeService {

	private final AttendeeRepository attendeeRepository;
	private final InterestRepository interestRepository;
	private final MemberInterestRepository memberInterestRepository;

	@Transactional
	public Set<Interest> addInterests(Attendee attendee, Set<Long> interestIds) {
		Attendee managedAttendee = attendeeRepository.findById(attendee.getId())
			.orElseThrow(() -> new EntityNotFoundException("Attendee not found with id: " + attendee.getId()));

		Set<Interest> existingInterests = managedAttendee.getMemberInterests()
			.stream()
			.map(MemberInterest::getInterest)
			.collect(Collectors.toSet());

		Set<Interest> newInterests = interestRepository.findAllById(interestIds)
			.stream()
			.filter(interest -> !existingInterests.contains(interest))
			.collect(Collectors.toSet());

		if (newInterests.isEmpty()) {
			return existingInterests;
		}

		Set<MemberInterest> memberInterestList = newInterests.stream()
			.map(interest -> new MemberInterest(managedAttendee, interest))
			.collect(Collectors.toSet());

		memberInterestRepository.saveAll(memberInterestList);
		managedAttendee.getMemberInterests().addAll(memberInterestList);

		return managedAttendee.getMemberInterests()
			.stream()
			.map(MemberInterest::getInterest)
			.collect(Collectors.toSet());
	}
}
