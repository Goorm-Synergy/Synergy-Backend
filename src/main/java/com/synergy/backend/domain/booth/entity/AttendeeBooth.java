package com.synergy.backend.domain.booth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class AttendeeBooth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendee_booth_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "booth_id")
    private Booth booth;

    @Column(name = "attendee_id", nullable = false)
    private Long attendeeId;

    public AttendeeBooth(Booth booth, Long attendeeId) {
        this.booth = booth;
        this.attendeeId = attendeeId;
    }
}
