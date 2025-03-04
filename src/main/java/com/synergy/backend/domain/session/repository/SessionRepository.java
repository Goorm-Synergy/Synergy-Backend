package com.synergy.backend.domain.session.repository;

import com.synergy.backend.domain.session.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
