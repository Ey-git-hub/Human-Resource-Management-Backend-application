package com.hrms.organization.controller;

import com.hrms.organization.dto.OrganizationRequestDto;
import com.hrms.organization.dto.OrganizationResponseDto;
import com.hrms.organization.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationResponseDto> create(@Valid @RequestBody OrganizationRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(organizationService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrganizationResponseDto>> getAll() {
        return ResponseEntity.ok(organizationService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizationResponseDto> update(@PathVariable Long id, @Valid @RequestBody OrganizationRequestDto dto) {
        return ResponseEntity.ok(organizationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        organizationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
