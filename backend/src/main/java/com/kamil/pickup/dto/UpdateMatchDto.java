package com.kamil.pickup.dto;

import com.kamil.pickup.model.MatchStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record UpdateMatchDto(
        @NotNull(message = "groupId is required")
        Long groupId,

        @NotNull(message = "fieldId is required")
        Long fieldId,

        @NotNull(message = "matchDateTime is required")
        @FutureOrPresent(message = "matchDateTime must be now or in the future")
        LocalDateTime matchDateTime,

        @NotNull(message = "maxPlayers is required")
        @Min(value = 1, message = "maxPlayers must be at least 1")
        Integer maxPlayers,

        @NotNull(message = "status is required")
        MatchStatus status
) {}
