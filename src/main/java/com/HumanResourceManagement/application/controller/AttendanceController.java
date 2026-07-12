package com.HumanResourceManagement.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HumanResourceManagement.application.dto.AttendanceRequest;
import com.HumanResourceManagement.application.dto.AttendanceResponse;
// import com.HumanResourceManagement.application.model.AttendanceStatus;
import com.HumanResourceManagement.application.service.AttendanceService;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    @GetMapping
    public ResponseEntity<List<AttendanceResponse>> getMethodName() {
        return ResponseEntity.ok(attendanceService.getAllAttendance());
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
    public ResponseEntity<AttendanceResponse> newAttendance(@RequestBody AttendanceRequest request) {
        AttendanceResponse created = attendanceService.addNewAttendance(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

}
