package com.kamil.pickup.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateDisplayNameDto(
        @NotBlank
        String displayName
) {}