package com.HumanResourceManagement.Attendance.Service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.HumanResourceManagement.Attendance.DTO.AttendanceRequest;
import com.HumanResourceManagement.Attendance.DTO.AttendanceResponse;

public interface AttendanceServiceInterface{
    Page<AttendanceResponse> getAllAttendance(Pageable pageable);

    Optional<AttendanceResponse> getAttendanceById(Long id);

    AttendanceResponse addNewAttendance(AttendanceRequest request);
}
