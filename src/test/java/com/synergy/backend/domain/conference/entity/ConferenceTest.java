package com.synergy.backend.domain.conference.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("잘못된 값으로 컨퍼런스를 만드는 시나리오")
    @TestFactory
    Collection<DynamicTest> ofException() {
        // given
        TimePeriod timePeriod = TimePeriod.of(LocalDateTime.of(2025, 4, 14, 9, 0), LocalDateTime.of(2025, 4, 15, 16, 0));

        // when & then
        return List.of(
                DynamicTest.dynamicTest("컨퍼런스 제목은 공백으로 작성할 수 없습니다..", () -> {
                        //given
                        String name = "  ";
                        String location = "컨퍼런스 위치";
                        // when & then
                        assertThatThrownBy(() -> Conference.of(name, timePeriod, location))
                                .hasMessage("Name is invalid")
                                .isInstanceOf(IllegalArgumentException.class);
                    }
                ),
                DynamicTest.dynamicTest("컨퍼런스 제목은 50자 이내 입니다.", () -> {
                            //given
                            String name = "123456789/123456789/123456789/123456789/123456789/1";
                            String location = "컨퍼런스 위치";
                            // when & then
                            assertThatThrownBy(() -> Conference.of(name, timePeriod, location))
                                    .hasMessage("Name is invalid")
                                    .isInstanceOf(IllegalArgumentException.class);
                        }
                )
        );
    }


}