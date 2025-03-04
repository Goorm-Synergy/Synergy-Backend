package com.synergy.backend.domain.conference.model;


import com.synergy.backend.domain.booth.model.Booth;
import com.synergy.backend.domain.session.entity.Session;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.REMOVE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Conference {

    public static final int MAX_NAME_LENGTH = 50;
    public static final int MAX_LOCATION_LENGTH = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conference_id")
    private Long id;

    @Column(nullable = false, length = MAX_NAME_LENGTH)
    private String name;

    @Embedded
    private TimePeriod period;

    @Column(nullable = false, length = MAX_LOCATION_LENGTH)
    private String location;

    @OneToMany(mappedBy = "conference", cascade = {CascadeType.PERSIST, REMOVE}, orphanRemoval = true)
    private List<Session> sessions = new ArrayList<>();

    @OneToMany(mappedBy = "conference", cascade = {CascadeType.PERSIST, REMOVE}, orphanRemoval = true)
    private List<Booth> booths = new ArrayList<>();

    private Conference(String name, TimePeriod period, String location) {
        this.name = name;
        this.period = period;
        this.location = location;
    }

    public static Conference of(String name, TimePeriod period, String location) {
        if (name.length() > MAX_NAME_LENGTH || name.isBlank()) {
            throw new IllegalArgumentException("Name is too long");
        }

        if (location.length() > MAX_LOCATION_LENGTH || location.isBlank()) {
            throw new IllegalArgumentException("Location is too long");
        }
        return new Conference(name, period, location);
    }
}
