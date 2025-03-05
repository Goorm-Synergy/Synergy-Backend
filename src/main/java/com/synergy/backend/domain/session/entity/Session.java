package com.synergy.backend.domain.session.entity;


import com.synergy.backend.domain.conference.model.Conference;
import com.synergy.backend.domain.session.dto.SessionReqDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Long id;

    @NotNull
    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 100)
    private String speaker;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false, length = 3000)
    private String description;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "conference_id")
    private Conference conference;

    @Builder
    public Session(SessionReqDto reqDto, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = reqDto.title();
        this.speaker = reqDto.speaker();
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = reqDto.description();
    }

    public void updateSession(SessionReqDto reqDto, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = reqDto.title();
        this.speaker = reqDto.speaker();
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = reqDto.description();
    }
}
