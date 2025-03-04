package com.synergy.backend.global.common;

import lombok.Builder;

@Builder
public record CommonResponse<T>(
	String status,
	String message,
	T item
) {
}
