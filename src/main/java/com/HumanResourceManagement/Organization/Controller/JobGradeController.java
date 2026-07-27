package com.HumanResourceManagement.Organization.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.HumanResourceManagement.Organization.DTO.JobGradeRequest;
import com.HumanResourceManagement.Organization.DTO.JobGradeResponse;
import com.HumanResourceManagement.Organization.Service.JobGradeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job-grades")
@RequiredArgsConstructor
public class JobGradeController {

    private final JobGradeService jobGradeService;

    @PostMapping
    public ResponseEntity<JobGradeResponse> create(@Valid @RequestBody JobGradeRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobGradeService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobGradeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(jobGradeService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<JobGradeResponse>> getAll() {
        return ResponseEntity.ok(jobGradeService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobGradeResponse> update(@PathVariable Long id,
            @Valid @RequestBody JobGradeRequest dto) {
        return ResponseEntity.ok(jobGradeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jobGradeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
