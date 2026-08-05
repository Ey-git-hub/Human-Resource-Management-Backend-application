package com.HumanResourceManagement.Attendance.DTO;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

import com.HumanResourceManagement.Attendance.Model.WorkSchedule.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class WorkScheduleResponse {
    private Long id;
    private Long employeeId;
    private String employeeName;
    private Long shiftId;
    private String shiftName;
    private LocalDate effectiveDate;
    private LocalDate endDate;
    private Set<DayOfWeek> daysOfWeek;
    private Status status;
}
