package com.kamil.pickup.dto;

import java.time.LocalDateTime;

public record GroupDto(
        Long id,
        String name,
        String description,
        Long ownerId,
        LocalDateTime createdAt
) {}
