package com.kamil.pickup.controller;

import com.kamil.pickup.dto.*;
import com.kamil.pickup.model.Group;
import com.kamil.pickup.model.User;
import com.kamil.pickup.service.GroupService;
import com.kamil.pickup.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {

    private final GroupService groupService;
    private final UserService userService;


    public GroupController(GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<GroupDto>> list(@RequestParam(required = false) Long ownerId) {
        List<Group> groups;
        if (ownerId != null) {
            User owner = userService.findById(ownerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found"));
            groups = groupService.listOwnedBy(owner);
        } else {
            groups = groupService.findAll();
        }
        var dtos = groups.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getById(@PathVariable Long id) {
        Group g = groupService.findById(id);
        return ResponseEntity.ok(toDto(g));
    }

    @GetMapping("/{id}/members")
    public ResponseEntity<List<UserDto>> listMembers(@PathVariable Long id) {
        List<User> members = groupService.listMembers(id);
        var dtos = members.stream()
                .map(u -> new UserDto(
                        u.getId(),
                        u.getEmail(),
                        u.getDisplayName(),
                        u.getIsGuest(),
                        u.getCreatedAt()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<GroupDto> create(@Valid @RequestBody CreateGroupDto dto) {
        User owner = userService.findById(dto.ownerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found"));
        Group created = groupService.createGroup(
                dto.name(),
                dto.description(),
                owner
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(created));
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<GroupDto> updateName(
            @PathVariable Long id,
            @Valid @RequestBody UpdateGroupNameDto dto
    ) {
        Group updated = groupService.updateName(id, dto.name());
        return ResponseEntity.ok(toDto(updated));
    }

    @PutMapping("/{id}/description")
    public ResponseEntity<GroupDto> updateDescription(
            @PathVariable Long id,
            @Valid @RequestBody UpdateGroupDescriptionDto dto
    ) {
        Group updated = groupService.updateDescription(id, dto.description());
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        groupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteByName(
            @RequestParam String name
    ) {
        groupService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }

    private GroupDto toDto(Group g) {
        return new GroupDto(
                g.getId(),
                g.getName(),
                g.getDescription(),
                g.getOwner().getId(),
                g.getCreatedAt()
        );
    }
}
