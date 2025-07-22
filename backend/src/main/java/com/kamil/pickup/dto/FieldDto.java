package com.kamil.pickup.dto;

import java.time.LocalDateTime;

public record FieldDto(
        Long id,
        String name,
        String address,
        Double latitude,
        Double longitude,
        LocalDateTime createdAt
) {}
