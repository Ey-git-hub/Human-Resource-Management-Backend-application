package com.hrms.organization.controller;

import com.hrms.organization.dto.PositionRequestDto;
import com.hrms.organization.dto.PositionResponseDto;
import com.hrms.organization.service.PositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @PostMapping
    public ResponseEntity<PositionResponseDto> create(@Valid @RequestBody PositionRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(positionService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(positionService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<PositionResponseDto>> getAll() {
        return ResponseEntity.ok(positionService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PositionResponseDto> update(@PathVariable Long id, @Valid @RequestBody PositionRequestDto dto) {
        return ResponseEntity.ok(positionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        positionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
