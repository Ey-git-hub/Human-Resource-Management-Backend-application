package com.HumanResourceManagement.Attendance.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.HumanResourceManagement.Attendance.DTO.ShiftRequest;
import com.HumanResourceManagement.Attendance.DTO.ShiftResponse;
import com.HumanResourceManagement.Attendance.Service.ShiftServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shifts")
public class ShiftController {

    private final ShiftServiceInterface shiftService;

    @GetMapping
    public ResponseEntity<Page<ShiftResponse>> getAllShifts(Pageable pageable) {
        return ResponseEntity.ok(shiftService.fetchAllShifts(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftResponse> getShift(@PathVariable Long id) {
        return shiftService.getShift(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ShiftResponse> createShift(@Valid @RequestBody ShiftRequest request) {
        ShiftResponse created = shiftService.createShift(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShiftResponse> updateShift(@PathVariable Long id, @Valid @RequestBody ShiftRequest request) {
        return ResponseEntity.ok(shiftService.updateShift(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShift(@PathVariable Long id) {
        shiftService.deleteShift(id);
        return ResponseEntity.noContent().build();
    }
}
