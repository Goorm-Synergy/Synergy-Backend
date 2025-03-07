package com.synergy.backend.domain.member.api.dto;

import java.util.Set;

import com.synergy.backend.domain.interest.entity.Interest;

public record InterestRequestDto(Set<Long> interestIds) {
}
