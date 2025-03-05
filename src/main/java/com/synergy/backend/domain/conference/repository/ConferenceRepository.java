package com.synergy.backend.domain.conference.repository;

import com.synergy.backend.domain.conference.entity.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {
}
