package com.kamil.pickup.controller;

import com.kamil.pickup.dto.CreateFieldDto;
import com.kamil.pickup.dto.FieldDto;
import com.kamil.pickup.dto.UpdateFieldDto;
import com.kamil.pickup.model.Field;
import com.kamil.pickup.service.FieldService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/fields")
public class FieldController {

    private final FieldService fieldService;

    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    /**
     * List all fields, or search by name fragment or address fragment.
     *
     * GET /api/v1/fields
     * GET /api/v1/fields?name=park
     * GET /api/v1/fields?address=Main
     */
    @GetMapping
    public ResponseEntity<List<FieldDto>> list(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address
    ) {
        List<Field> fields;
        if (name != null) {
            fields = fieldService.searchByName(name);
        } else if (address != null) {
            fields = fieldService.searchByAddress(address);
        } else {
            fields = fieldService.findAll();
        }
        var dtos = fields.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FieldDto> getById(@PathVariable Long id) {
        Field f;
        try {
            f = fieldService.findById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.ok(toDto(f));
    }

    @PostMapping
    public ResponseEntity<FieldDto> create(@Valid @RequestBody CreateFieldDto dto) {
        // protect against duplicates
        if (fieldService.existsByName(dto.name())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Field name already exists");
        }
        if (fieldService.existsByAddress(dto.address())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Field address already exists");
        }

        Field toSave = new Field(
                dto.name(),
                dto.address(),
                dto.latitude(),
                dto.longitude()
        );
        Field created = fieldService.save(toSave);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FieldDto> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateFieldDto dto
    ) {
        Field f;
        try {
            f = fieldService.findById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        // apply updates
        f.setName(dto.name());
        f.setAddress(dto.address());
        f.setLatitude(dto.latitude());
        f.setLongitude(dto.longitude());

        Field updated = fieldService.save(f);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        // will throw if not found
        try {
            fieldService.findById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        fieldService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteByName(@RequestParam String name) {
        if (!fieldService.existsByName(name)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Field not found with name " + name);
        }
        fieldService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }

    private FieldDto toDto(Field f) {
        return new FieldDto(
                f.getId(),
                f.getName(),
                f.getAddress(),
                f.getLatitude(),
                f.getLongitude(),
                f.getCreatedAt()
        );
    }
}
