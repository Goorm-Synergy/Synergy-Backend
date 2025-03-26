package com.synergy.backend.domain.booth.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synergy.backend.domain.booth.service.BoothParticipationService;
import com.synergy.backend.domain.member.entity.RoleType;
import com.synergy.backend.global.annotation.SwaggerSummaryRole;
import com.synergy.backend.global.common.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/verify")
public class BoothVerifyController {

	private final BoothParticipationService boothParticipationService;

	@SwaggerSummaryRole({RoleType.ATTENDEE})
	@Operation(summary = "부스 참여", description = "부스 ID를 기준으로 해당 부스에 참여합니다.")
	@PostMapping("/{boothId}/participate/{attendeeId}")
	public ApiResponse<String> participateInBooth(
		@PathVariable Long conferenceId,
		@PathVariable Long boothId,
		@PathVariable Long attendeeId) {
		boothParticipationService.participateInBooth(attendeeId, boothId);
		return ApiResponse.ok("부스 참여가 완료되었습니다.", 201);
	}
}
