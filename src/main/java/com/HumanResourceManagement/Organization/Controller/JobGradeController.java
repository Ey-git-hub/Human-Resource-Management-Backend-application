package com.hrms.organization.controller;

import com.hrms.organization.dto.JobGradeRequestDto;
import com.hrms.organization.dto.JobGradeResponseDto;
import com.hrms.organization.service.JobGradeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job-grades")
@RequiredArgsConstructor
public class JobGradeController {

    private final JobGradeService jobGradeService;

    @PostMapping
    public ResponseEntity<JobGradeResponseDto> create(@Valid @RequestBody JobGradeRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobGradeService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobGradeResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(jobGradeService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<JobGradeResponseDto>> getAll() {
        return ResponseEntity.ok(jobGradeService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobGradeResponseDto> update(@PathVariable Long id, @Valid @RequestBody JobGradeRequestDto dto) {
        return ResponseEntity.ok(jobGradeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jobGradeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
