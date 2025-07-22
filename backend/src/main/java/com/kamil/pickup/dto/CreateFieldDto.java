package com.kamil.pickup.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateFieldDto(
        @NotBlank
        String name,
        String address,

        @NotNull
        Double latitude,

        @NotNull
        Double longitude

) {}
