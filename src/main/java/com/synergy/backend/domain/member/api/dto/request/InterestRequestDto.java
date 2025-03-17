package com.synergy.backend.domain.member.api.dto.request;

import java.util.Set;

import com.synergy.backend.domain.interest.entity.Interest;

public record InterestRequestDto(Set<String> interests) {
}
