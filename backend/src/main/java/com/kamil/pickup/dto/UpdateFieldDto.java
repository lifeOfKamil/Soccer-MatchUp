package com.kamil.pickup.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateFieldDto(
        @NotBlank
        String name,
        @NotBlank
        String address,
        @NotNull
        Double latitude,
        @NotNull
        Double longitude
) {}
