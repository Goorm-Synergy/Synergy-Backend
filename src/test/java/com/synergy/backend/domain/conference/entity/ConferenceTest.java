package com.synergy.backend.domain.conference.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ConferenceTest {

    @DisplayName("컨퍼런스 정보를 등록합니다.")
    @Test
    void of() {
        // given
        String name = "컨퍼런스 제목";
        TimePeriod timePeriod = TimePeriod.of(LocalDateTime.of(2025, 4, 14, 9, 0), LocalDateTime.of(2025, 4, 15, 16, 0));
        String location = "컨퍼런스 위치";
        // when
        Conference conference = Conference.of(name, timePeriod, location);
        // then
        assertThat(conference)
                .extracting("name", "location")
                .containsExactly(name, location);
    }


}