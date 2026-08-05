package com.HumanResourceManagement.Attendance.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.HumanResourceManagement.Attendance.Model.OvertimeRequest.Status;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class OvertimeRequestResponse {
    private Long id;
    private Long employeeId;
    private String employeeName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private BigDecimal hours;
    private String reason;
    private Status status;
    private Long approvedById;
    private String approvedByName;
    private BigDecimal rateMultiplier;
}
