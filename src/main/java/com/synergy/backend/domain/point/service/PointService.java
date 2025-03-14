package com.synergy.backend.domain.point.service;

import java.util.List;

import com.synergy.backend.domain.point.api.dto.PointResponseDto;
import com.synergy.backend.domain.point.entity.Point;
import com.synergy.backend.domain.point.entity.PointType;

public interface PointService {

	List<Point> getPointHistory(Long attendeeId);
	PointResponseDto getPointResponse(Long pointId);
	void addPoint(Long attendeeId, PointType pointType, Long id);
}
