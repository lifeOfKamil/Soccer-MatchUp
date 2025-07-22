package com.kamil.pickup.dto;

import com.kamil.pickup.model.Role;
import jakarta.validation.constraints.NotNull;

public record ChangeRoleDto(
        @NotNull
        Role newRole
) {}
