package com.synergy.backend.domain.booth.controller;

import com.synergy.backend.domain.booth.dto.BoothRequestDto;
import com.synergy.backend.domain.booth.dto.BoothResponseDto;
import com.synergy.backend.domain.booth.service.BoothService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booth")
@RequiredArgsConstructor
public class BoothController {

    private final BoothService boothService;

    @GetMapping("/{id}")
    public ResponseEntity<BoothResponseDto> getBoothById(@PathVariable Long id) {
        return ResponseEntity.ok(boothService.getBoothById(id));
    }

    @GetMapping
    public ResponseEntity<List<BoothResponseDto>> getAllBooths() {
        return ResponseEntity.ok(boothService.getAllBooths());
    }

    @PostMapping
    public ResponseEntity<BoothResponseDto> createBooth(@RequestBody BoothRequestDto request) {
        return ResponseEntity.ok(boothService.createBooth(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BoothResponseDto> updateBooth(@PathVariable Long id, @RequestBody BoothRequestDto request) {
        return ResponseEntity.ok(boothService.updateBooth(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooth(@PathVariable Long id) {
        boothService.deleteBooth(id);
        return ResponseEntity.noContent().build();
    }
}
