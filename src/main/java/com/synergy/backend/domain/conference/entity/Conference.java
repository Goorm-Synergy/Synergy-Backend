package com.synergy.backend.domain.conference.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conference_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String conferenceName;

    @Column(nullable = false)
    private LocalDate conferencingDate; // 명명 규칙으로 인한 컬럼명 변경

    @Column(nullable = false, length = 100)
    private String location;
}
