package com.HumanResourceManagement.Leave.Services;

import java.util.List;

import com.HumanResourceManagement.Leave.DTO.LeaveRequest;
import com.HumanResourceManagement.Leave.DTO.LeaveResponse;
import com.HumanResourceManagement.Leave.Model.LeaveStatus;

public interface LeaveServiceInterface {
    LeaveResponse createLeaveRequest(LeaveRequest request);

    List<LeaveResponse> getAllLeaveRequests(LeaveStatus status);

    LeaveResponse getLeaveById(Long id);

    List<LeaveResponse> getLeavesByEmployee(Long employeeId);
}
