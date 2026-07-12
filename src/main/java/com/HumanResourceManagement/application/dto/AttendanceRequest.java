package com.HumanResourceManagement.application.dto;

import java.time.LocalDate;
// import java.time.LocalDateTime;
import java.time.LocalTime;

// import org.hibernate.annotations.CreationTimestamp;

import com.HumanResourceManagement.application.model.AttendanceStatus;

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
