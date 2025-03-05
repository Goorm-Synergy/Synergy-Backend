package com.synergy.backend.domain.conference.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceUpdateResponse {

    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;

}
