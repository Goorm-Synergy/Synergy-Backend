package com.synergy.backend.domain.booth.dto;

import com.synergy.backend.domain.booth.entity.Booth;
import lombok.Getter;

@Getter
public class BoothResponseDto {
    private Long id;
    private String name;
    private String company;
    private String location;
    private String description;

    public BoothResponseDto(Booth booth) {
        this.id = booth.getId();
        this.name = booth.getName();
        this.company = booth.getCompany();
        this.location = booth.getLocation();
        this.description = booth.getDescription();
    }
}
