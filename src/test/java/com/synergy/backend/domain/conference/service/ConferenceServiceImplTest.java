package com.synergy.backend.domain.conference.service;

import com.synergy.backend.domain.conference.dto.requset.ConferenceCreateRequest;
import com.synergy.backend.domain.conference.dto.response.ConferenceCreateResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class ConferenceServiceImplTest {
    @Autowired
    ConferenceService conferenceService;

    @DisplayName("")
    @Test
    void register() {
        // given
        ConferenceCreateRequest request = new ConferenceCreateRequest(
                "컨퍼런스명",
                LocalDateTime.of(2025,5,10,9,0),
                LocalDateTime.of(2025,5,11,18,0),
                "부천시 오정구 고강동 311-25 1층"
        );
        // when
        ConferenceCreateResponse result = conferenceService.register(request);
        // then
        assertThat(result)
                .isNotNull()
                .extracting(ConferenceCreateResponse::id)
                .isNotNull();
    }

}