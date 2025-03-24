package com.synergy.backend.domain.member.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synergy.backend.domain.member.api.dto.request.EmailVerificationConfirmDto;
import com.synergy.backend.domain.member.api.dto.request.EmailVerificationRequestDto;
import com.synergy.backend.domain.member.api.dto.request.LoginAdminRequestDto;
import com.synergy.backend.domain.member.api.dto.request.LoginAttendeeRequestDto;
import com.synergy.backend.domain.member.api.dto.request.PasswordResetConfirmDto;
import com.synergy.backend.domain.member.api.dto.request.PasswordResetRequestDto;
import com.synergy.backend.domain.member.api.dto.request.SignupAttendeeRequestDto;
import com.synergy.backend.domain.member.api.dto.resposne.TokenResponseDto;
import com.synergy.backend.domain.member.service.AuthService;
import com.synergy.backend.global.annotation.DisableSwaggerSecurity;
import com.synergy.backend.global.common.ApiResponse;
import com.synergy.backend.global.mail.MailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Auth Controller", description = "인증/인가 API 제공")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	private final MailService mailService;

	@Operation(summary = "참가자 회원가입", description = "이메일 인증을 완료한 참가자가 회원가입을 합니다.")
	@DisableSwaggerSecurity
	@PostMapping("/attendee/signup")
	public ApiResponse<?> registerAttendee(@Valid @RequestBody SignupAttendeeRequestDto request) {
		return ApiResponse.ok(authService.registerAttendee(request), 201);
	}

	@Operation(summary = "참가자 로그인", description = "참가자가 이메일과 비밀번호로 로그인하여 액세스/리프레시 토큰을 발급받습니다.")
	@DisableSwaggerSecurity
	@PostMapping("/attendee/login")
	public ApiResponse<TokenResponseDto> loginAttendee(@RequestBody LoginAttendeeRequestDto request) {
		return ApiResponse.ok(authService.loginAsAttendee(request.email(), request.password()), 200);
	}

	@Operation(summary = "관리자/리크루터 로그인", description = "인증코드를 통해 관리자 또는 채용담당자가 로그인합니다.")
	@DisableSwaggerSecurity
	@PostMapping("/admin/login")
	public ApiResponse<TokenResponseDto> loginAdmin(@RequestBody LoginAdminRequestDto request) {
		return ApiResponse.ok(authService.loginAsAdminOrRecruiter(request.adminAuthCode()), 200);
	}

	@Operation(summary = "비밀번호 재설정 요청", description = "본인 인증을 위해 이메일, 이름, 전화번호를 입력해 비밀번호 재설정을 요청합니다.")
	@DisableSwaggerSecurity
	@PostMapping("/password/reset/request")
	public ApiResponse<?> passwordResetRequest(@Valid @RequestBody PasswordResetRequestDto request) {
		authService.passwordResetRequest(request.email(), request.name(), request.phone());
		return ApiResponse.ok(null, 200);
	}

	@Operation(summary = "비밀번호 재설정", description = "본인 확인이 완료된 사용자가 새 비밀번호로 비밀번호를 재설정합니다.")
	@DisableSwaggerSecurity
	@PostMapping("/password/reset")
	public ApiResponse<?> newPassword(@Valid @RequestBody PasswordResetConfirmDto request) {
		authService.passwordReset(request.email(), request.newPassword());
		return ApiResponse.ok(null, 200);
	}

	@Operation(summary = "이메일 인증 요청", description = "입력한 이메일 주소로 인증번호를 전송합니다.")
	@DisableSwaggerSecurity
	@PostMapping("/email/verification/request")
	public ApiResponse<?> emailVerificationRequest(@Valid @RequestBody EmailVerificationRequestDto request) throws
		MessagingException {
		mailService.sendVerificationCodeToMail(request.email());
		return ApiResponse.ok(null, 200);
	}

	@Operation(summary = "이메일 인증 확인", description = "인증번호를 입력하여 이메일 인증을 완료합니다.")
	@DisableSwaggerSecurity
	@PostMapping("/email/verification/confirm")
	public ApiResponse<?> emailVerificationConfirm(@Valid @RequestBody EmailVerificationConfirmDto request) {
		mailService.mailVerificationConfirm(request.email(), request.code());
		return ApiResponse.ok(null, 200);
	}

}
