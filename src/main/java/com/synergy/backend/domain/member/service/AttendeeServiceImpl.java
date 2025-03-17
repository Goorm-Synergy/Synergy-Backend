package com.synergy.backend.domain.member.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synergy.backend.domain.interest.entity.AttendeeInterest;
import com.synergy.backend.domain.interest.entity.Interest;
import com.synergy.backend.domain.interest.exception.NotFoundInterestException;
import com.synergy.backend.domain.interest.repository.InterestRepository;
import com.synergy.backend.domain.interest.repository.MemberInterestRepository;
import com.synergy.backend.domain.member.entity.Attendee;
import com.synergy.backend.domain.member.exception.NotFoundUserException;
import com.synergy.backend.domain.member.repository.AttendeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {

	private final AttendeeRepository attendeeRepository;
	private final InterestRepository interestRepository;
	private final MemberInterestRepository memberInterestRepository;

	@Transactional
	@Override
	public Set<Interest> addInterests(String email, Set<String> interestNames) {
		Attendee attendee = findAttendeeByEmail(email);
		Set<Interest> interestsToAdd = getValidInterests(interestNames, attendee);

		// 추가할 관심 분야가 없으면 현재 등록된 관심 분야 반환
		if (interestsToAdd.isEmpty()) {
			return getCurrentInterests(attendee);
		}

		// 신규 관심 분야를 회원에 연결
		saveNewMemberInterests(attendee, interestsToAdd);
		return interestsToAdd;
	}

	private Attendee findAttendeeByEmail(String email) {
		return attendeeRepository.findByEmail(email)
			.orElseThrow(NotFoundUserException::new);
	}

	private Set<Interest> getValidInterests(Set<String> interestNames, Attendee attendee) {
		Set<Interest> interestsFound = new HashSet<>(interestRepository.findAllByNameIn(interestNames));

		// 존재하지 않는 관심사 검증
		validateInterestNames(interestNames, interestsFound);

		// 기존 등록된 관심사 필터링
		Set<String> existingInterestNames = getCurrentInterestNames(attendee);
		return interestsFound.stream()
			.filter(interest -> !existingInterestNames.contains(interest.getName()))
			.collect(Collectors.toSet());
	}

	private void validateInterestNames(Set<String> requestedNames, Set<Interest> foundInterests) {
		if (requestedNames.size() != foundInterests.size()) {
			Set<String> foundNames = foundInterests.stream()
				.map(Interest::getName)
				.collect(Collectors.toSet());

			Set<String> notFoundNames = requestedNames.stream()
				.filter(name -> !foundNames.contains(name))
				.collect(Collectors.toSet());

			throw new NotFoundInterestException(String.join(", ", notFoundNames));
		}
	}

	private Set<Interest> getCurrentInterests(Attendee attendee) {
		return attendee.getAttendeeInterests()
			.stream()
			.map(AttendeeInterest::getInterest)
			.collect(Collectors.toSet());
	}

	private Set<String> getCurrentInterestNames(Attendee attendee) {
		return attendee.getAttendeeInterests()
			.stream()
			.map(attendeeInterest -> attendeeInterest.getInterest().getName())
			.collect(Collectors.toSet());
	}

	private void saveNewMemberInterests(Attendee attendee, Set<Interest> newInterests) {
		Set<AttendeeInterest> attendeeInterestSet = newInterests.stream()
			.map(interest -> new AttendeeInterest(attendee, interest))
			.collect(Collectors.toSet());

		memberInterestRepository.saveAll(attendeeInterestSet);
		attendee.getAttendeeInterests().addAll(attendeeInterestSet);
	}
}
