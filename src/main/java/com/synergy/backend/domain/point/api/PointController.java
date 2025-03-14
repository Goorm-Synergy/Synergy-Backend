package com.synergy.backend.domain.point.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synergy.backend.domain.point.api.dto.PointResponseDto;
import com.synergy.backend.domain.point.entity.Point;
import com.synergy.backend.domain.point.service.PointService;
import com.synergy.backend.global.common.ApiResponse;
import com.synergy.backend.global.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/points")
@RequiredArgsConstructor
public class PointController {

	private final PointService pointService;

	@GetMapping("/my-points")
	public ApiResponse<?> getMyPoints(@AuthenticationPrincipal CustomUserDetails userDetails) {
		Long attendeeId = userDetails.getId();
		List<Point> points = pointService.getPointHistory(attendeeId);
		List<PointResponseDto> response = points.stream()
			.map(point -> pointService.getPointResponse(point.getId()))
			.collect(Collectors.toList());

		return ApiResponse.ok(response, 200);
	}
}
