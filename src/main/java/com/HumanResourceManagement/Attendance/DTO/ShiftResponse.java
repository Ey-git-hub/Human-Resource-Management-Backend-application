package com.HumanResourceManagement.Attendance.DTO;

import java.time.LocalTime;

import com.HumanResourceManagement.Attendance.Model.Shift.Status;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class ShiftResponse {
    private Long id;
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private int breakDuration;
    private int gracePeriodMinutes;
    private Status status;
}
