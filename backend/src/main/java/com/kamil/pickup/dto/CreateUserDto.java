package com.kamil.pickup.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDto(
  @NotBlank @Email String email,
  @NotBlank String password,
  @NotNull Boolean isGuest,
  String displayName
) {}
