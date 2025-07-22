package com.kamil.pickup.controller;

import com.kamil.pickup.dto.CreateMatchDto;
import com.kamil.pickup.dto.MatchDto;
import com.kamil.pickup.dto.UpdateMatchDto;
import com.kamil.pickup.model.Field;
import com.kamil.pickup.model.Group;
import com.kamil.pickup.model.Match;
import com.kamil.pickup.model.MatchStatus;
import com.kamil.pickup.service.FieldService;
import com.kamil.pickup.service.GroupService;
import com.kamil.pickup.service.MatchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/matches")
public class MatchController {

    private final MatchService matchService;
    private final GroupService groupService;
    private final FieldService fieldService;

    public MatchController(MatchService matchService, GroupService groupService, FieldService fieldService) {
        this.matchService = matchService;
        this.groupService = groupService;
        this.fieldService = fieldService;
    }

    /**
     * GET /api/v1/matches
     * Optional filters:
     *   ?groupId=…            → all for that group
     *   ?groupId=…&upcoming=true → upcoming for that group
     *   ?fieldId=…            → all for that field
     *   ?upcoming=true        → all upcoming
     *   ?status=OPEN          → all by status
     */
    @GetMapping
    public ResponseEntity<List<MatchDto>> list(
            @RequestParam(required = false) Long groupId,
            @RequestParam(required = false) Long fieldId,
            @RequestParam(required = false) Boolean upcoming,
            @RequestParam(required = false) MatchStatus status
    ) {
        List<Match> matches;
        if (groupId != null && Boolean.TRUE.equals(upcoming)) {
            matches = matchService.findUpcomingForGroup(groupId);
        } else if (groupId != null) {
            matches = matchService.findByGroup(groupId);
        } else if (fieldId != null) {
            matches = matchService.findByField(fieldId);
        } else if (Boolean.TRUE.equals(upcoming)) {
            matches = matchService.findUpcoming();
        } else if (status != null) {
            matches = matchService.findByStatus(status);
        } else {
            matches = matchService.findAll();
        }

        var dtos = matches.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDto> getById(@PathVariable Long id) {
        Match m;
        try {
            m = matchService.getById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.ok(toDto(m));
    }

    @PostMapping
    public ResponseEntity<MatchDto> create(@Valid @RequestBody CreateMatchDto dto) {
        Group g = groupService.findById(dto.groupId());
        Field f = fieldService.findById(dto.fieldId());

        Match m = new Match();
        m.setGroup(g);
        m.setField(f);
        m.setMatchDateTime(dto.matchDateTime());
        m.setMaxPlayers(dto.maxPlayers());
        m.setStatus(dto.status());

        Match created = matchService.save(m);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchDto> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMatchDto dto
    ) {
        Match m;
        try {
            m = matchService.getById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        // Possibly re‑resolve group/field
        Group g = groupService.findById(dto.groupId());
        Field f = fieldService.findById(dto.fieldId());

        m.setGroup(g);
        m.setField(f);
        m.setMatchDateTime(dto.matchDateTime());
        m.setMaxPlayers(dto.maxPlayers());
        m.setStatus(dto.status());

        Match updated = matchService.save(m);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            matchService.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }

    private MatchDto toDto(Match m) {
        return new MatchDto(
                m.getId(),
                m.getGroup().getId(),
                m.getField().getId(),
                m.getMatchDateTime(),
                m.getMaxPlayers(),
                m.getStatus(),
                m.getCreatedAt()
        );
    }
}
