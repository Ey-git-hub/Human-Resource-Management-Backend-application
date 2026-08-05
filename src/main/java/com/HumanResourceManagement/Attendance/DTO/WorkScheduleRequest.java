package com.HumanResourceManagement.Attendance.DTO;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

import com.HumanResourceManagement.Attendance.Model.WorkSchedule.Status;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WorkScheduleRequest {
    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotNull(message = "Shift ID is required")
    private Long shiftId;

    @NotNull(message = "Effective date is required")
    private LocalDate effectiveDate;

    private LocalDate endDate;

    private Set<DayOfWeek> daysOfWeek;

    @NotNull(message = "Status is required")
    private Status status = Status.ACTIVE;
}
