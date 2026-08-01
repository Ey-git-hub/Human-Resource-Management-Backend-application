package com.HumanResourceManagement.Attendance.Service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HumanResourceManagement.Attendance.DTO.AttendanceRequest;
import com.HumanResourceManagement.Attendance.DTO.AttendanceResponse;
import com.HumanResourceManagement.Attendance.Mapper.AttendanceMapper;
import com.HumanResourceManagement.Attendance.Model.Attendance;
import com.HumanResourceManagement.Attendance.Repository.AttendanceRepository;
import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.Employee.Repository.EmployeeRepository;
import com.HumanResourceManagement.shared.exception.DuplicateResourceException;
import com.HumanResourceManagement.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
    private final AttendanceMapper attendanceMapper;

    public Page<AttendanceResponse> getAllAttendance(Pageable pageable) {
        return attendanceRepository.findAll(pageable)
                .map(attendanceMapper::toResponse);
    }

    public Optional<AttendanceResponse> getAttendanceById(Long id) {
        return attendanceRepository.findById(id).map(attendanceMapper::toResponse);
    }

    @Transactional
    public AttendanceResponse addNewAttendance(AttendanceRequest request) {
        // 1. Verify that the employee exists
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> ResourceNotFoundException.of("Employee", request.getEmployeeId()));

        // 2. Prevent duplicate entries for the exact same date
        if (attendanceRepository.findByEmployeeIdAndDate(request.getEmployeeId(), request.getDate()).isPresent()) {
            throw new DuplicateResourceException("An attendance record already exists for this employee on this date.");
        }

        // 3. Map the request onto a new entity via the mapper
        Attendance attendance = attendanceMapper.toEntity(request, employee);

        // 4. Save to database
        Attendance savedAttendance = attendanceRepository.save(attendance);

        // 5. Convert entity back to the response DTO
        return attendanceMapper.toResponse(savedAttendance);
    }

}
