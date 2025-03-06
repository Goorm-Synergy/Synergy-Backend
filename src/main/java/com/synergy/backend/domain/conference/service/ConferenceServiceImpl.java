package com.synergy.backend.domain.conference.service;


import com.synergy.backend.domain.conference.dto.requset.ConferenceCreateRequest;
import com.synergy.backend.domain.conference.dto.requset.ConferenceUpdateRequest;
import com.synergy.backend.domain.conference.dto.response.ConferenceCreateResponse;
import com.synergy.backend.domain.conference.dto.response.ConferenceUpdateResponse;
import com.synergy.backend.domain.conference.entity.Conference;
import com.synergy.backend.domain.conference.entity.TimePeriod;
import com.synergy.backend.domain.conference.exception.NotFoundConference;
import com.synergy.backend.domain.conference.repository.ConferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConferenceServiceImpl implements ConferenceService {
    private final ConferenceRepository conferenceRepository;
    // private final SessionRepository sessionRepository

    @Transactional
    @Override
    public ConferenceCreateResponse register(ConferenceCreateRequest request) {
        TimePeriod timePeriod = TimePeriod.of(request.startDate(), request.endDate());
        Conference conference = Conference.of(request.name(), timePeriod, request.location());
        Conference savedConference = conferenceRepository.save(conference);

        return ConferenceCreateResponse.from(savedConference);
    }

    @Transactional
    @Override
    public ConferenceUpdateResponse update(Long conferenceId, ConferenceUpdateRequest request) {
        // 컨퍼런스 조회 -> 예외 처리
        Conference findConference = conferenceRepository.findById(conferenceId)
                .orElseThrow(NotFoundConference::new);
        //dto 필드 유무 확인
        Optional.ofNullable(request.getName()).ifPresent((name) -> findConference.updateName(name));
        Optional<LocalDateTime> startTime = Optional.ofNullable(request.getStartTime());
        Optional<LocalDateTime> endTime = Optional.ofNullable(request.getEndTime());
        if(startTime.isPresent() && endTime.isPresent()) {
            findConference.updatePeriod(TimePeriod.of(startTime.get(), endTime.get()));
        }
        Optional.ofNullable(request.getLocation()).ifPresent((location) -> findConference.updateLocation(location));
        // 컨퍼런스 정보 변경

        return ConferenceUpdateResponse.from(findConference); // 반영된 정보 반환
    }
}
