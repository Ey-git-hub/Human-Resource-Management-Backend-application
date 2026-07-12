package com.HumanResourceManagement.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HumanResourceManagement.application.dto.AttendanceResponse;
// import com.HumanResourceManagement.application.model.AttendanceStatus;
import com.HumanResourceManagement.application.service.AttendanceService;

import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    @GetMapping
    public ResponseEntity<List<AttendanceResponse>> getMethodName() {
        return ResponseEntity.ok(attendanceService.getAllAttendance());
    }

}
