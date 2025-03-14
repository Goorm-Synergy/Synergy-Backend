package com.synergy.backend.domain.point.service;

import java.util.List;

import com.synergy.backend.domain.point.api.dto.PointResponseDto;
import com.synergy.backend.domain.point.entity.Point;

public interface PointService {

	List<Point> getPointHistory(Long attendeeId);
	PointResponseDto getPointResponse(Long pointId);
}
