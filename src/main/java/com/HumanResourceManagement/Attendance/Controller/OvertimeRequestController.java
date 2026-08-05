package com.HumanResourceManagement.Attendance.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.HumanResourceManagement.Attendance.DTO.OvertimeRequestRequest;
import com.HumanResourceManagement.Attendance.DTO.OvertimeRequestResponse;
import com.HumanResourceManagement.Attendance.Service.OvertimeRequestServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/overtime-requests")
public class OvertimeRequestController {

    private final OvertimeRequestServiceInterface service;

    @GetMapping
    public ResponseEntity<Page<OvertimeRequestResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.fetchAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OvertimeRequestResponse> getById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OvertimeRequestResponse> create(@Valid @RequestBody OvertimeRequestRequest request) {
        OvertimeRequestResponse created = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OvertimeRequestResponse> update(@PathVariable Long id, @Valid @RequestBody OvertimeRequestRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
