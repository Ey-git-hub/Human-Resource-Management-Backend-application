package com.HumanResourceManagement.application.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import com.HumanResourceManagement.application.dto.AttendanceRequest;
import com.HumanResourceManagement.application.dto.AttendanceResponse;
import com.HumanResourceManagement.application.model.Employee;
import com.HumanResourceManagement.application.repository.AttendanceRepository;
import com.HumanResourceManagement.application.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

// import org.jspecify.annotations.Nullable;
@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public List<AttendanceResponse> getAllAttendance() {
        return attendanceRepository.findAll().stream().map(AttendanceResponse::fromAttendance)
                .collect(Collectors.toList());

    }

    public Optional<AttendanceResponse> getAttendanceById(Long id) {
        return attendanceRepository.findById(id).map(AttendanceResponse::fromAttendance);
    }

    public AttendanceResponse addNewAttendance(AttendanceRequest request) {
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(
                        () -> new IllegalArgumentException("Employee not found with id: " + request.getEmployeeId()));

        if (attendanceRepository.findByEmployeeIdAndDate(request.getEmployeeId(), request.getDate()).isPresent()) {
            throw new IllegalStateException("An attendance record already exists for this employee on this date.");
            return employee;
        }

    }

}
