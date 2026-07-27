package com.hrms.organization.controller;

import com.hrms.organization.dto.BranchRequestDto;
import com.hrms.organization.dto.BranchResponseDto;
import com.hrms.organization.service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<BranchResponseDto> create(@Valid @RequestBody BranchRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(branchService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(branchService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<BranchResponseDto>> getAll() {
        return ResponseEntity.ok(branchService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchResponseDto> update(@PathVariable Long id, @Valid @RequestBody BranchRequestDto dto) {
        return ResponseEntity.ok(branchService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        branchService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
