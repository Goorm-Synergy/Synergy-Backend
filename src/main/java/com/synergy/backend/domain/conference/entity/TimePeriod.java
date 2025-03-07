package com.synergy.backend.domain.conference.entity;

import com.synergy.backend.domain.conference.exception.InvalidTimePeriodException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class TimePeriod {

    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @Column(nullable = false)
    private LocalDateTime endDateTime;

    private TimePeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public static TimePeriod of(LocalDateTime startDateTime, LocalDateTime endDate) {
        if(startDateTime.isAfter(endDate) || startDateTime.isEqual(endDate)) {
            throw new InvalidTimePeriodException();
        }
        return new TimePeriod(startDateTime, endDate);
    }

}
