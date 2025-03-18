package com.synergy.backend.domain.techstack.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergy.backend.domain.techstack.entity.TechStack;

public interface TechStackRepository extends JpaRepository<TechStack, Long> {

	List<TechStack> findAllByCode(Set<Integer> codes);
}
