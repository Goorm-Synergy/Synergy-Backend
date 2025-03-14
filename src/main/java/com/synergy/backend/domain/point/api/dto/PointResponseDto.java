package com.synergy.backend.domain.point.api.dto;

import com.synergy.backend.domain.point.entity.Point;

public record PointResponseDto(
	String title,
	Integer point,
	String details
) {
	public static PointResponseDto from(Point point, String details) {
		return new PointResponseDto(
			point.getPointType().getMessage(),
			point.getPointType().getPointValue(),
			details
		);
	}
}
