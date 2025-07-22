package com.kamil.pickup.dto;

import com.kamil.pickup.model.Role;
import jakarta.validation.constraints.NotNull;

public record CreateMembershipDto(
        @NotNull(message = "userId is required")
        Long userId,

        @NotNull(message = "groupId is required")
        Long groupId,

        @NotNull(message = "role is required")
        Role role
) {}
