package com.HumanResourceManagement.Leave.DTO;

import java.time.LocalDate;

import com.HumanResourceManagement.Leave.Model.LeaveType;

// import com.HumanResourceManagement.application.model.Leave.LeaveType;

import lombok.Data;

@Data
public class leaveRequest {
    private LeaveType leaveType;
    private LocalDate leaveDate;
    private LocalDate endDate;
    private String reason;
}
