package com.HumanResourceManagement.Attendance.DTO;

import java.time.LocalDate;
// import java.time.LocalDateTime;
import java.time.LocalTime;

import com.HumanResourceManagement.Attendance.Model.AttendanceStatus;

import lombok.Data;

// import com.HumanResourceManagement.application.model.Employee;
@Data
public class AttendanceRequest {

    private Long id;
    private String employeeName;
    private Long employeeId;
    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private AttendanceStatus status;

}
