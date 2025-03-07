package com.synergy.backend.domain.booth.controller;

import com.synergy.backend.domain.booth.dto.BoothRequestDto;
import com.synergy.backend.domain.booth.dto.BoothResponseDto;
import com.synergy.backend.domain.booth.service.BoothService;
import com.synergy.backend.global.common.ApiResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/conference/{conferenceId}/booth")
@RequiredArgsConstructor
public class BoothController {

    private final BoothService boothService;

    // 특정 Conference의 부스 조회
    @GetMapping("/{id}")
    public ApiResponse<BoothResponseDto> getBoothById(@PathVariable Long conferenceId, @PathVariable Long id) {
        return ApiResponse.ok(boothService.getBoothById(conferenceId, id), 200);
    }

    // 특정 Conference에 속한 모든 부스 조회
    @GetMapping
    public ApiResponse<List<BoothResponseDto>> getAllBooths(@PathVariable Long conferenceId) {
        return ApiResponse.ok(boothService.getAllBooths(conferenceId), 200);
    }

    // 특정 Conference에 부스 추가
    @PostMapping
    public ApiResponse<BoothResponseDto> createBooth(@PathVariable Long conferenceId, @RequestBody BoothRequestDto request) {
        return ApiResponse.ok(boothService.createBooth(conferenceId, request), 201);
    }

    // 특정 Conference의 부스 수정
    @PatchMapping("/{id}")
    public ApiResponse<BoothResponseDto> updateBooth(@PathVariable Long conferenceId, @PathVariable Long id, @RequestBody BoothRequestDto request) {
        return ApiResponse.ok(boothService.updateBooth(conferenceId, id, request), 200);
    }

    // 특정 Conference의 부스 삭제
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteBooth(@PathVariable Long conferenceId, @PathVariable Long id) {
        boothService.deleteBooth(conferenceId, id);
        return ApiResponse.ok(null, 204);
    }
}
