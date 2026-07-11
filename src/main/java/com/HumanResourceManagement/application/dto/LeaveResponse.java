package com.HumanResourceManagement.application.dto;

import com.HumanResourceManagement.application.model.Leave;
import com.HumanResourceManagement.application.model.LeaveStatus;
import com.HumanResourceManagement.application.model.LeaveType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveResponse {
    private Long id;
    private LeaveType leaveType;
    private Long employeeId;
    private String employeeName;
    private LocalDate leaveDate;
    private LocalDate endDate;
    private String reason;
    private LeaveStatus status;

    public static LeaveResponse fromEntity(Leave leave) {
        LeaveResponse response = new LeaveResponse();
        response.setId(leave.getId());
        response.setLeaveType(leave.getLeaveType());
        response.setStatus(leave.getStatus());
        response.setLeaveDate(leave.getStartDate());
        response.setEndDate(leave.getEndDate());
        if (leave.getEmployee() != null) {
            response.setEmployeeName(leave.getEmployee().getFirstName() + " " + leave.getEmployee().getLastName());
            response.setEmployeeId(leave.getEmployee().getId());
        }
        return response;
    }
}
