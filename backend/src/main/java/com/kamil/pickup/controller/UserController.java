package com.kamil.pickup.controller;

import com.kamil.pickup.dto.ChangePasswordDto;
import com.kamil.pickup.dto.CreateUserDto;
import com.kamil.pickup.dto.UpdateDisplayNameDto;
import com.kamil.pickup.dto.UserDto;
import com.kamil.pickup.model.User;
import com.kamil.pickup.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll(@RequestParam(required = false) Long groupId) {
        List<User> users = (groupId != null)
                ? userService.getMembersOfGroup(groupId)
                : userService.findAll();
        List<UserDto> dtos = users.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return userService.findById(id).map(u -> ResponseEntity.ok(toDto(u))).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody CreateUserDto dto) {
        User created = dto.isGuest()
                ? userService.createGuest(dto.email(), dto.displayName())
                : userService.register(dto.email(), dto.password(), dto.displayName());
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(created));
    }

    @PutMapping
    public ResponseEntity<UserDto> updateDisplayName(
            @PathVariable Long id,
            @Valid @RequestBody UpdateDisplayNameDto dto
    ) {
        User updated = userService.updateDisplayName(id, dto.displayName());
        return ResponseEntity.ok(toDto(updated));
    }

    @PutMapping
    public ResponseEntity<Void> changePassword(
            @PathVariable Long id,
            @Valid @RequestBody ChangePasswordDto dto
    ) {
        userService.changePassword(id, dto.newPassword());
        return ResponseEntity.noContent().build();
    }

    private UserDto toDto(User u) {
        return new UserDto(
                u.getId(),
                u.getEmail(),
                u.getDisplayName(),
                u.getIsGuest(),
                u.getCreatedAt()
        );
    }
}
