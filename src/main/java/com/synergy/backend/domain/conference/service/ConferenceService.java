package com.synergy.backend.domain.conference.service;

import com.synergy.backend.domain.conference.dto.requset.ConferenceCreateRequest;
import com.synergy.backend.domain.conference.dto.requset.ConferenceUpdateRequest;
import com.synergy.backend.domain.conference.dto.response.ConferenceCreateResponse;
import com.synergy.backend.domain.conference.dto.response.ConferenceUpdateResponse;

public interface ConferenceService {
    ConferenceCreateResponse register(ConferenceCreateRequest request);

    ConferenceUpdateResponse update(Long conferenceId, ConferenceUpdateRequest request);
}
