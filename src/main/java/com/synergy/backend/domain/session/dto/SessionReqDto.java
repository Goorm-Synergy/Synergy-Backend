package com.synergy.backend.domain.session.dto;

import lombok.Getter;

@Getter
public record SessionReqDto(String title,
        String speaker,
        String startTime,
        String endTime,
        String description) {


}
