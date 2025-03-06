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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConferenceServiceImpl implements ConferenceService {
    private final ConferenceRepository conferenceRepository;

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
        Conference findConference = conferenceRepository.findById(conferenceId)
                .orElseThrow(NotFoundConference::new);
        applyUpdatesToConference(request, findConference);

        return ConferenceUpdateResponse.from(findConference); // 반영된 정보 반환
    }

    private void applyUpdatesToConference(ConferenceUpdateRequest request, Conference findConference) {
        Optional.ofNullable(request.getName()).ifPresent(findConference::updateName);
        Optional.ofNullable(request.getLocation()).ifPresent(findConference::updateLocation);

        Optional<LocalDateTime> optionalStartTime = Optional.ofNullable(request.getStartTime());
        Optional<LocalDateTime> optionalEndTime = Optional.ofNullable(request.getEndTime());
        if(optionalStartTime.isPresent() && optionalEndTime.isPresent()) {
            findConference.updatePeriod(TimePeriod.of(optionalStartTime.get(), optionalEndTime.get()));
        }

    }
}
