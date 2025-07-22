package com.kamil.pickup.dto;

import com.kamil.pickup.model.Role;

import java.time.LocalDateTime;

public record MembershipDto(
        Long id,
        Long userId,
        Long groupId,
        Role role,
        LocalDateTime joinedAt
) {}
