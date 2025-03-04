package com.synergy.backend.global.common;

import lombok.Builder;

@Builder
public record ApiResponse<T>(
	String status,
	int code,
	String message,
	T data
) {

	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	public static <T> ApiResponse<T> ok(T data, int code) {
		return ApiResponse.<T>builder()
			.status(SUCCESS)
			.data(data)
			.code(code)
			.build();
	}

	public static <T> ApiResponse<T> error(String errorMessage, int code) {
		return ApiResponse.<T>builder()
			.status(ERROR)
			.message(errorMessage)
			.code(code)
			.build();
	}
}
