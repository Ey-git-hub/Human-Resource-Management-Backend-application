package com.HumanResourceManagement.application.dto;

import com.HumanResourceManagement.application.model.LeaveType;

import java.time.LocalDate;

public class leaveRequest {
    private LeaveType leaveType;
    private LocalDate leaveDate;
    private LocalDate endDate;
    private String reason;
}
