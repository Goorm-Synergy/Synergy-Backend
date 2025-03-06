package com.synergy.backend.domain.booth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoothRequestDto {
    private String name;
    private String company;
    private String location;
    private String description;
}
