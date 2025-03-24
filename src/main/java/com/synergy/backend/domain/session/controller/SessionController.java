package com.synergy.backend.domain.session.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.WriterException;
import com.synergy.backend.domain.session.dto.sessionDto.SessionDetailResDto;
import com.synergy.backend.domain.session.dto.sessionDto.SessionReqDto;
import com.synergy.backend.domain.session.dto.sessionDto.SessionResDto;
import com.synergy.backend.domain.session.service.SessionService;
import com.synergy.backend.global.common.ApiResponse;
import com.synergy.backend.global.security.CustomUserDetails;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Session Controller", description = "세션 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/conference/{conferenceId}/session")
public class SessionController {

	private final SessionService sessionService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ApiResponse createSession(@AuthenticationPrincipal CustomUserDetails user,
		@PathVariable(name = "conferenceId") Long conferenceId,
		@RequestPart @Valid SessionReqDto sessionReqDto,
		@RequestPart MultipartFile multipartFile) throws WriterException {
		sessionService.createSession(user.getIdentifier(), conferenceId, sessionReqDto, multipartFile);

		return ApiResponse.ok("Session created successfully!", 200);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'ATTENDEE', 'RECRUITER')")
	@GetMapping
	public ApiResponse getSessions(@AuthenticationPrincipal CustomUserDetails user,
		@PathVariable(name = "conferenceId") Long conferenceId) {
		List<SessionResDto> result = sessionService.getSessions(user.getIdentifier(), conferenceId);

		return ApiResponse.ok(result, 200);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'ATTENDEE', 'RECRUITER')")
	@GetMapping("/{sessionId}")
	public ApiResponse getSession(@AuthenticationPrincipal CustomUserDetails user,
		@PathVariable(name = "conferenceId") Long conferenceId,
		@PathVariable(name = "sessionId") Long sessionId) {
		SessionDetailResDto result = sessionService.getSessionInfo(user.getIdentifier(), user.getRole(), conferenceId,
			sessionId);

		return ApiResponse.ok(result, 200);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping
	public ApiResponse updateSession(@AuthenticationPrincipal CustomUserDetails user,
		@RequestParam Long sessionId,
		@RequestPart @Valid SessionReqDto sessionReqDto,
		@RequestPart MultipartFile multipartFile) {
		sessionService.updateSession(user.getIdentifier(), sessionId, sessionReqDto, multipartFile);

		return ApiResponse.ok("Session updated successfully!", 200);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping
	public ApiResponse deleteSession(@AuthenticationPrincipal CustomUserDetails user,
		@RequestParam Long sessionId) {
		sessionService.deleteSession(user.getIdentifier(), sessionId);

		return ApiResponse.ok("Session deleted successfully!", 200);
	}

}
