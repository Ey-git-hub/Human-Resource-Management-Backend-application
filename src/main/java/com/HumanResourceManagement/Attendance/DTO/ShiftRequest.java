package com.HumanResourceManagement.Attendance.DTO;

import java.time.LocalTime;

import com.HumanResourceManagement.Attendance.Model.Shift.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShiftRequest {
    @NotBlank(message = "Shift name is required")
    private String name;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    @NotNull(message = "Break duration is required")
    private Integer breakDuration;

    @NotNull(message = "Grace period is required")
    private Integer gracePeriodMinutes;

    @NotNull(message = "Status is required")
    private Status status = Status.ACTIVE;
}
