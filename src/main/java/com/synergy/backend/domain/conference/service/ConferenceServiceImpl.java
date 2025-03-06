package com.synergy.backend.domain.conference.service;


import com.synergy.backend.domain.conference.dto.requset.ConferenceCreateRequest;
import com.synergy.backend.domain.conference.dto.requset.ConferenceUpdateRequest;
import com.synergy.backend.domain.conference.dto.response.ConferenceCreateResponse;
import com.synergy.backend.domain.conference.dto.response.ConferenceUpdateResponse;
import com.synergy.backend.domain.conference.entity.Conference;
import com.synergy.backend.domain.conference.repository.ConferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConferenceServiceImpl implements ConferenceService {
    private final ConferenceRepository conferenceRepository;
    // private final SessionRepository sessionRepository

    @Transactional
    @Override
    public ConferenceCreateResponse register(ConferenceCreateRequest request) {
        // sessionRepository의 기능 사용
        // conferenceRepository 기능 사용
        // 등 비즈니스 처리
        return null;
    }

    @Override
    public ConferenceUpdateResponse update(Long conferenceId, ConferenceUpdateRequest request) {
        // 컨퍼런스 조회 -> 예외 처리

        //dto 필드 유무 확인

        // 컨퍼런스 정보 변경

        return null; // 반영된 정보 반환
    }
}
