package com.synergy.backend.domain.session.dto;

import java.time.LocalDateTime;

public record SessionDetailResDto(
        String sessionId,
        String title,
        String speaker,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String description
) {
}
