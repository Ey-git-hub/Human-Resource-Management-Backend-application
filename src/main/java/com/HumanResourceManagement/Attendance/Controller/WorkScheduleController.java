package com.HumanResourceManagement.Attendance.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.HumanResourceManagement.Attendance.DTO.WorkScheduleRequest;
import com.HumanResourceManagement.Attendance.DTO.WorkScheduleResponse;
import com.HumanResourceManagement.Attendance.Service.WorkScheduleServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/work-schedules")
public class WorkScheduleController {

    private final WorkScheduleServiceInterface service;

    @GetMapping
    public ResponseEntity<Page<WorkScheduleResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.fetchAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkScheduleResponse> getById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<WorkScheduleResponse> create(@Valid @RequestBody WorkScheduleRequest request) {
        WorkScheduleResponse created = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkScheduleResponse> update(@PathVariable Long id, @Valid @RequestBody WorkScheduleRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
