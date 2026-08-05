package com.HumanResourceManagement.Attendance.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.HumanResourceManagement.Attendance.Model.OvertimeRequest.Status;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OvertimeRequestRequest {
    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    @NotNull(message = "Hours is required")
    private BigDecimal hours;

    private String reason;

    private Status status = Status.PENDING;

    private Long approvedById;

    private BigDecimal rateMultiplier;
}
