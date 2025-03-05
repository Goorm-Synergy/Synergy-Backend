package com.synergy.backend.domain.session.dto;

public record SessionReqDto(String title,
        String speaker,
        String startTime,
        String endTime,
        String description) {


}
