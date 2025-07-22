package com.kamil.pickup.dto;

import java.time.LocalDateTime;

public record UserDto(
    Long id,
    String email,
    String displayName,
    Boolean isGuest,
    LocalDateTime createdAt
) {}