package com.HumanResourceManagement.Attendance.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HumanResourceManagement.Attendance.DTO.AttendanceRequest;
import com.HumanResourceManagement.Attendance.DTO.AttendanceResponse;
import com.HumanResourceManagement.Attendance.Service.AttendanceServiceInterface;
import com.HumanResourceManagement.shared.util.PageableUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attendance")
public class AttendanceController {
    private final AttendanceServiceInterface attendanceService;

    @GetMapping
    public ResponseEntity<Page<AttendanceResponse>> getAllAttendance(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(attendanceService.getAllAttendance(PageableUtils.build(page, size, sortBy, direction)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponse> getAttendanceById(@PathVariable Long id) {
        Optional<AttendanceResponse> result = attendanceService.getAttendanceById(id);
        boolean found = result.isPresent();
        if (found) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AttendanceResponse> newAttendance(@Valid @RequestBody AttendanceRequest request) {
        AttendanceResponse created = attendanceService.addNewAttendance(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
