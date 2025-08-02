package com.kamil.pickup.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateGroupNameDto(
        @NotBlank
        String name
) {}