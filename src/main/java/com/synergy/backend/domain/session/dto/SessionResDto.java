package com.synergy.backend.domain.session.dto;

import com.synergy.backend.domain.session.entity.Session;

import java.time.LocalDateTime;

public record SessionResDto(
        Long id,
        String title,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
    public SessionResDto(Session session){
        this(session.getId(), session.getTitle(), session.getStartTime(), session.getEndTime());
    }
}
