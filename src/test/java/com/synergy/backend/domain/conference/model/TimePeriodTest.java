package com.synergy.backend.domain.conference.model;

import com.synergy.backend.domain.conference.exception.InvalidTimePeriodException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
class TimePeriodTest {

    @DisplayName("기간을 설정합니다.")
    @Test
    void of() {
        // given
        LocalDateTime startDateTime = LocalDateTime.of(2020, 3, 20, 9, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2020, 3, 21, 18, 0);
        // when
        TimePeriod timePeriod = TimePeriod.of(startDateTime, endDateTime);
        // then
        assertThat(timePeriod)
                .extracting("startDateTime", "endDateTime")
                .containsExactly(LocalDateTime.of(2020, 3, 20, 9, 0), LocalDateTime.of(2020, 3, 21, 18, 0));

    }

    @DisplayName("시작 날짜는 종료 날짜보다 빠릅니다.")
    @Test
    void ofException() {
        // given
        LocalDateTime startDateTime = LocalDateTime.of(2020, 3, 20, 9, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2020, 3, 20, 9, 0);
        // when & then
        assertThatThrownBy(() -> TimePeriod.of(startDateTime, endDateTime))
                .hasMessage("시작 날짜는 더 빨라야 합니다.")
                .isInstanceOf(InvalidTimePeriodException.class);
    }


  
}