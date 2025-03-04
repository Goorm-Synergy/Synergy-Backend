package com.synergy.backend.global.common;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ApiResponseAdvice implements ResponseBodyAdvice<ApiResponse<?>> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return ApiResponse.class.isAssignableFrom(returnType.getParameterType());
	}

	@Override
	public ApiResponse<?> beforeBodyWrite(ApiResponse<?> body, MethodParameter returnType,
		MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
		ServerHttpRequest request, ServerHttpResponse response) {

		int statusCode = 200;
		if (response instanceof ServletServerHttpResponse servletResponse) {
			statusCode = servletResponse.getServletResponse().getStatus();
		}

		return ApiResponse.builder()
			.status(body.status())
			.message(body.message())
			.data(body.data())
			.code(statusCode)
			.build();
	}
}
