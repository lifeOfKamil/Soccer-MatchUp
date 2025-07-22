package com.kamil.pickup.controller;

import com.kamil.pickup.dto.ChangeRoleDto;
import com.kamil.pickup.dto.CreateMembershipDto;
import com.kamil.pickup.dto.MembershipDto;
import com.kamil.pickup.model.Group;
import com.kamil.pickup.model.Membership;
import com.kamil.pickup.model.User;
import com.kamil.pickup.service.GroupService;
import com.kamil.pickup.service.MembershipService;
import com.kamil.pickup.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class MembershipController {

    private final MembershipService membershipService;
    private final UserService userService;
    private final GroupService groupService;

    public MembershipController(MembershipService membershipService, UserService userService, GroupService groupService) {
        this.membershipService = membershipService;
        this.userService = userService;
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<MembershipDto>> listAll() {
        List<Membership> all = membershipService.findAll();
        var dtos = all.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembershipDto> getById(@PathVariable Long id) {
        try {
            Membership m = membershipService.getById(id);
            return ResponseEntity.ok(toDto(m));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<MembershipDto> create(
            @Valid @RequestBody CreateMembershipDto dto
    ) {
        User user  = userService.findById(dto.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Group group = groupService.findById(dto.groupId());
        Membership created = membershipService.addUserToGroup(user.getId(), group.getId(), dto.role());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toDto(created));
    }

    @PutMapping
    public ResponseEntity<MembershipDto> changeRole(
            @RequestParam Long userId,
            @RequestParam Long groupId,
            @Valid @RequestBody ChangeRoleDto dto
    ) {
        try {
            Membership updated = membershipService.changeRole(userId, groupId, dto.newRole());
            return ResponseEntity.ok(toDto(updated));
        } catch (NoSuchElementException | IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> remove(
            @RequestParam Long userId,
            @RequestParam Long groupId
    ) {
        membershipService.removeUserFromGroup(userId, groupId);
        return ResponseEntity.noContent().build();
    }

    private MembershipDto toDto(Membership m) {
        return new MembershipDto(
                m.getId(),
                m.getUser().getId(),
                m.getGroup().getId(),
                m.getRole(),
                m.getJoinedAt()
        );
    }
}
