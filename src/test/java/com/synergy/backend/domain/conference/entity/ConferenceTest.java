package com.synergy.backend.domain.conference.entity;

import com.synergy.backend.domain.conference.exception.InvalidLocationException;
import com.synergy.backend.domain.conference.exception.InvalidNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static com.synergy.backend.domain.conference.exception.ErrorType._INVALID_CONFERENCE_LOCATION;
import static com.synergy.backend.domain.conference.exception.ErrorType._INVALID_CONFERENCE_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConferenceTest {

    @DisplayName("컨퍼런스 정보를 등록합니다.")
    @Test
    void of() {
        // given
        String name = "컨퍼런스 제목";
        TimePeriod timePeriod = TimePeriod.of(LocalDateTime.of(2025, 4, 14, 9, 0), LocalDateTime.of(2025, 4, 15, 16, 0));
        String organizer = "김승진";
        String location = "컨퍼런스 위치";
        String type = "IT";
        // when
        Conference conference = Conference.of(name, timePeriod, organizer, location, type);
        // then
        assertThat(conference)
                .extracting("name", "organizer", "location", "type")
                .containsExactly(name, organizer, location, type);
    }

    @DisplayName("잘못된 컨퍼런스명 형식으로 등록하는 시나리오")
    @TestFactory
    Collection<DynamicTest> ofExceptionName() {
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
                                    .hasMessage(_INVALID_CONFERENCE_NAME.getMessage())
                                    .isInstanceOf(InvalidNameException.class);
                        }
                ),
                DynamicTest.dynamicTest("컨퍼런스 제목은 50자 이내 입니다.", () -> {
                            //given
                            String name = "123456789/123456789/123456789/123456789/123456789/1";
                            String location = "컨퍼런스 위치";
                            // when & then
                            assertThatThrownBy(() -> Conference.of(name, timePeriod, location))
                                    .hasMessage(_INVALID_CONFERENCE_NAME.getMessage())
                                    .isInstanceOf(InvalidNameException.class);
                        }
                )
        );
    }

    @DisplayName("잘못된 형식으로 위치명을 등록하는 시나리오")
    @TestFactory
    Collection<DynamicTest> ofExceptionLocation() {
        // given
        TimePeriod timePeriod = TimePeriod.of(LocalDateTime.of(2025, 4, 14, 9, 0), LocalDateTime.of(2025, 4, 15, 16, 0));

        // when & then
        return List.of(
                DynamicTest.dynamicTest("컨퍼런스 위치는 공백으로 작성할 수 없습니다..", () -> {
                            //given
                            String name = "컨퍼런스명";
                            String location = "  ";
                            // when & then
                            assertThatThrownBy(() -> Conference.of(name, timePeriod, location))
                                    .hasMessage(_INVALID_CONFERENCE_LOCATION.getMessage())
                                    .isInstanceOf(InvalidLocationException.class);
                        }
                ),
                DynamicTest.dynamicTest("컨퍼런스 위치는 100자 이내 입니다.", () -> {
                            //given
                            String name = "컨퍼런스명";
                            String location = "123456789/123456789/123456789/123456789/123456789" +
                                    "/123456789/123456789/123456789/123456789/123456789/1";

                            // when & then
                            assertThatThrownBy(() -> Conference.of(name, timePeriod, location))
                                    .hasMessage(_INVALID_CONFERENCE_LOCATION.getMessage())
                                    .isInstanceOf(InvalidLocationException.class);
                        }
                )
        );
    }

}