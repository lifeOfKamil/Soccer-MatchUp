package com.kamil.pickup.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateGroupDto(
        @NotBlank String name,
        String description,
        @NotNull Long ownerId
) {}