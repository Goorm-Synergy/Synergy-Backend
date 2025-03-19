package com.synergy.backend.domain.member.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergy.backend.domain.member.api.dto.AttendeeRankingResponseDto;
import com.synergy.backend.domain.member.service.AdminService;
import com.synergy.backend.global.common.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

	private final AdminService adminService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/attendee-rankings")
	public ApiResponse<Page<AttendeeRankingResponseDto>> getAttendeeRankings(
		@RequestParam(required = false) String grade, // 등급 필터링 (선택)
		@PageableDefault(size = 10, sort = "totalPoints", direction = Sort.Direction.DESC) Pageable pageable
	) {
		return ApiResponse.ok(adminService.getAttendeeRankings(grade, pageable), 200);
	}
}
