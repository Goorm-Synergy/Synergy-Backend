package com.synergy.backend.domain.member.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synergy.backend.domain.interest.entity.AttendeeInterest;
import com.synergy.backend.domain.interest.entity.Interest;
import com.synergy.backend.domain.interest.exception.NotFoundInterestException;
import com.synergy.backend.domain.interest.repository.AttendeeInterestRepository;
import com.synergy.backend.domain.interest.repository.InterestRepository;
import com.synergy.backend.domain.member.api.dto.request.JobInfoRequestDto;
import com.synergy.backend.domain.member.entity.Attendee;
import com.synergy.backend.domain.member.exception.NotFoundUserException;
import com.synergy.backend.domain.member.repository.AttendeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {

	private final AttendeeRepository attendeeRepository;
	private final InterestRepository interestRepository;
	private final AttendeeInterestRepository attendeeInterestRepository;

	@Transactional
	@Override
	public Set<Interest> addInterests(String email, Set<Integer> interestCodes) {
		Attendee attendee = findAttendeeByEmail(email);

		// 요청된 숫자 코드에 해당하는 Interest 엔티티 조회
		Set<Interest> interestsToAdd = getValidInterests(interestCodes);

		// 현재 등록된 관심사 가져오기
		Set<Interest> currentInterests = getCurrentInterests(attendee);

		// 현재 등록된 관심사를 제외한 새로운 관심사 필터링
		Set<Interest> newInterests = interestsToAdd.stream()
			.filter(interest -> !currentInterests.contains(interest))
			.collect(Collectors.toSet());

		// 새로운 관심사가 있다면 저장
		if (!newInterests.isEmpty()) {
			saveNewMemberInterests(attendee, newInterests);
		}

		// 최종 등록된 관심사 반환
		return getCurrentInterests(attendee);
	}

	@Override
	public void addJobInfo(String email, JobInfoRequestDto request) {

	}

	private Attendee findAttendeeByEmail(String email) {
		return attendeeRepository.findByEmail(email)
			.orElseThrow(NotFoundUserException::new);
	}

	private Set<Interest> getValidInterests(Set<Integer> interestCodes) {
		Map<Integer, Interest> interestMap = interestRepository.findAllByCodeIn(interestCodes)
			.stream()
			.collect(Collectors.toMap(Interest::getCode, Function.identity()));

		// 요청된 코드 중 존재하지 않는 값 찾기
		if (interestMap.size() != interestCodes.size()) {
			Set<Integer> notFoundCodes = interestCodes.stream()
				.filter(code -> !interestMap.containsKey(code))
				.collect(Collectors.toSet());

			throw new NotFoundInterestException("Not found interests: " + notFoundCodes);
		}

		return new HashSet<>(interestMap.values());
	}

	private Set<Interest> getCurrentInterests(Attendee attendee) {
		return attendee.getAttendeeInterests()
			.stream()
			.map(AttendeeInterest::getInterest)
			.collect(Collectors.toSet());
	}

	private void saveNewMemberInterests(Attendee attendee, Set<Interest> newInterests) {
		Set<AttendeeInterest> newAttendeeInterests = newInterests.stream()
			.map(interest -> new AttendeeInterest(attendee, interest))
			.collect(Collectors.toSet());

		attendeeInterestRepository.saveAll(newAttendeeInterests);
		attendee.getAttendeeInterests().addAll(newAttendeeInterests);
	}
}
