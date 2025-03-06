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
        String name = "컨퍼런스명";
        LocalDateTime startTime = LocalDateTime.of(2024, 3, 5, 13, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 3, 5, 20, 0);
        String location = "뉴욕 뉴져지";

        Conference savedConference = conferenceRepository.save(
                Conference.of(name, TimePeriod.of(startTime, endTime), location)
        );
        Long conferenceId = savedConference.getId();

        String updatedName = "카카오 개발자로 살아남기";
        LocalDateTime updatedStartTime = LocalDateTime.of(2024, 3, 5, 15, 0);
        LocalDateTime updatedEndTime = LocalDateTime.of(2024, 3, 5, 19, 0);
        String updatedLocation = "서울 여의도 국회 1층";
        // when & then
        return List.of(
                DynamicTest.dynamicTest("컨퍼런스명을 수정합니다.", () -> {
                        //given

                        ConferenceUpdateRequest request = new ConferenceUpdateRequest(
                                updatedName, null, null, null
                        );
                        //when
                        ConferenceUpdateResponse result = conferenceService.update(conferenceId, request);
                        //then
                            assertThat(result)
                                    .extracting(ConferenceUpdateResponse::name,
                                            ConferenceUpdateResponse::startTime,
                                            ConferenceUpdateResponse::endTime,
                                            ConferenceUpdateResponse::location)
                                    .containsExactly(updatedName,
                                            savedConference.getPeriod().getStartDateTime(),
                                            savedConference.getPeriod().getEndDateTime(),
                                            savedConference.getLocation()
                                    );
                    }
                ),
                DynamicTest.dynamicTest("위치 정보를 수정합니다.", () -> {
                            //given
                            ConferenceUpdateRequest request = new ConferenceUpdateRequest(
                                    null, null, null, updatedLocation
                            );
                            //when
                            ConferenceUpdateResponse result = conferenceService.update(conferenceId, request);
                            //then
                            assertThat(result)
                                    .extracting(ConferenceUpdateResponse::name,
                                            ConferenceUpdateResponse::startTime,
                                            ConferenceUpdateResponse::endTime,
                                            ConferenceUpdateResponse::location)
                                    .containsExactly(
                                            updatedName,
                                            savedConference.getPeriod().getStartDateTime(),
                                            savedConference.getPeriod().getEndDateTime(),
                                            updatedLocation
                                    );
                        }
                ),
                DynamicTest.dynamicTest("컨퍼런스 기간을 수정합니다.", () -> {
                            //given
                            ConferenceUpdateRequest request = new ConferenceUpdateRequest(
                                    null, updatedStartTime, updatedEndTime, null
                            );
                            //when
                            ConferenceUpdateResponse result = conferenceService.update(conferenceId, request);
                            //then
                            assertThat(result)
                                    .extracting(ConferenceUpdateResponse::name,
                                            ConferenceUpdateResponse::startTime,
                                            ConferenceUpdateResponse::endTime,
                                            ConferenceUpdateResponse::location)
                                    .containsExactly(
                                            updatedName,
                                            updatedStartTime,
                                            updatedEndTime,
                                            updatedLocation
                                    );
                        }
                )
        );
    }

}