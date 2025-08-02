package com.kamil.pickup.controller;

import com.kamil.pickup.dto.*;
import com.kamil.pickup.model.Team;
import com.kamil.pickup.model.User;
import com.kamil.pickup.service.TeamService;
import com.kamil.pickup.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/groups")
public class GroupController {

    private final TeamService teamService;
    private final UserService userService;

    @Autowired
    public GroupController(TeamService teamService, UserService userService) {
        this.teamService = teamService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<TeamDto>> list(@RequestParam(required = false) Long ownerId) {
        List<Team> teams;
        if (ownerId != null) {
            User owner = userService.findById(ownerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found"));
            teams = teamService.listOwnedBy(owner);
        } else {
            teams = teamService.findAll();
        }
        var dtos = teams.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getById(@PathVariable Long id) {
        Team g = teamService.findById(id);
        return ResponseEntity.ok(toDto(g));
    }

    @GetMapping("/{id}/members")
    public ResponseEntity<List<UserDto>> listMembers(@PathVariable Long id) {
        List<User> members = teamService.listMembers(id);
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
    public ResponseEntity<TeamDto> create(@Valid @RequestBody CreateTeamDto dto) {
        User owner = userService.findById(dto.ownerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found"));
        Team created = teamService.createTeam(
                dto.name(),
                dto.description(),
                owner
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(created));
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<TeamDto> updateName(
            @PathVariable Long id,
            @Valid @RequestBody UpdateGroupNameDto dto
    ) {
        Team updated = teamService.updateName(id, dto.name());
        return ResponseEntity.ok(toDto(updated));
    }

    @PutMapping("/{id}/description")
    public ResponseEntity<TeamDto> updateDescription(
            @PathVariable Long id,
            @Valid @RequestBody UpdateGroupDescriptionDto dto
    ) {
        Team updated = teamService.updateDescription(id, dto.description());
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        teamService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteByName(
            @RequestParam String name
    ) {
        teamService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }

    private TeamDto toDto(Team g) {
        return new TeamDto(
                g.getId(),
                g.getName(),
                g.getDescription(),
                g.getOwner().getId(),
                g.getCreatedAt()
        );
    }
}
