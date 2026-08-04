package com.HumanResourceManagement.Leave.DTO;

// import com.HumanResourceManagement.application.model.Leave.Leave;
// import com.HumanResourceManagement.application.model.Leave.LeaveStatus;
// import com.HumanResourceManagement.application.model.Leave.LeaveType;

import lombok.Data;

import java.time.LocalDate;

import com.HumanResourceManagement.Leave.Model.LeaveStatus;
import com.HumanResourceManagement.Leave.Model.LeaveType;

@Data
public class LeaveResponse {
    private Long id;
    private LeaveType leaveType;
    private Long employeeId;
    private String employeeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private LeaveStatus status;
    private Long numberOfDays;
}
