package com.kamil.pickup.dto;

import com.kamil.pickup.model.MatchStatus;
import java.time.LocalDateTime;

public record MatchDto(
        Long id,
        Long groupId,
        Long fieldId,
        LocalDateTime matchDateTime,
        Integer maxPlayers,
        MatchStatus status,
        LocalDateTime createdAt
) {}
