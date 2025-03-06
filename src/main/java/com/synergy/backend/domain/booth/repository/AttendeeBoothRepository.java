package com.synergy.backend.domain.booth.repository;

import com.synergy.backend.domain.booth.entity.AttendeeBooth;
import com.synergy.backend.domain.booth.entity.Booth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendeeBoothRepository extends JpaRepository<AttendeeBooth, Long> {
    List<AttendeeBooth> findByBooth(Booth booth);
    boolean existsByBoothIdAndAttendeeId(Long boothId, Long attendeeId);
}
