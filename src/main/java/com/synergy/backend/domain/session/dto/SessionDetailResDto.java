package com.synergy.backend.domain.session.dto;

import com.synergy.backend.domain.session.entity.Session;

import java.time.LocalDateTime;

public record SessionDetailResDto(
        Long sessionId,
        String title,
        String speaker,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String description
) {
    public static SessionDetailResDto from(Session session) {
        return new SessionDetailResDto(session.getId(), session.getTitle(), session.getSpeaker(), session.getStartTime(), session.getEndTime(), session.getDescription());
    }
}
