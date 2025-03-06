package com.synergy.backend.domain.conference.service;

import com.synergy.backend.domain.conference.dto.requset.ConferenceCreateRequest;
import com.synergy.backend.domain.conference.dto.requset.ConferenceUpdateRequest;
import com.synergy.backend.domain.conference.dto.response.ConferenceCreateResponse;
import com.synergy.backend.domain.conference.dto.response.ConferenceUpdateResponse;
import com.synergy.backend.domain.conference.entity.Conference;
import com.synergy.backend.domain.conference.entity.TimePeriod;
import com.synergy.backend.domain.conference.exception.InvalidLocationException;
import com.synergy.backend.domain.conference.repository.ConferenceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static com.synergy.backend.domain.conference.exception.ErrorType._INVALID_CONFERENCE_LOCATION;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class ConferenceServiceImplTest {
    @Autowired
    ConferenceService conferenceService;
    @Autowired
    ConferenceRepository conferenceRepository;

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

    @DisplayName("컨퍼런스 정보 변경하는 시나리오")
    @TestFactory
    Collection<DynamicTest> update() {
        // given
        Conference savedConference = conferenceRepository.save(Conference.of("컨퍼런스명",
                TimePeriod.of(LocalDateTime.of(2024, 3, 5, 13, 0)
                        , LocalDateTime.of(2024, 3, 5, 20, 0)),
                "뉴욕 뉴져지"
        ));
        Long conferenceId = savedConference.getId();

        // when & then
        return List.of(
                DynamicTest.dynamicTest("컨퍼런스명, 시간을 수정합니다.", () -> {
                        //given
                        ConferenceUpdateRequest request = new ConferenceUpdateRequest(
                                "컨퍼런스명@@",
                                LocalDateTime.of(2024, 3, 5, 13, 0),
                                LocalDateTime.of(2024, 3, 5, 21, 0),
                                null
                        );
                        //when
                        ConferenceUpdateResponse result = conferenceService.update(conferenceId, request);
                        //then
                            assertThat(result)
                                    .extracting(ConferenceUpdateResponse::getName,
                                            ConferenceUpdateResponse::getStartTime,
                                            ConferenceUpdateResponse::getEndTime,
                                            ConferenceUpdateResponse::getLocation)
                                    .containsExactly("컨퍼런스명@@",
                                            LocalDateTime.of(2024, 3, 5, 13, 0),
                                            LocalDateTime.of(2024, 3, 5, 21, 0),
                                            "뉴욕 뉴져지"
                                    );

                    }
                )
        );
    }

}