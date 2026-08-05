package com.HumanResourceManagement.Employee.DTO;

import com.HumanResourceManagement.Employee.Model.EmploymentHistory;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data

@NoArgsConstructor
public class EmploymentHistoryResponse {
    private Long id;
    private Long employeeId;
    private Long previousPositionId;
    private Long newPositionId;
    private Long previousDepartmentId;
    private Long newDepartmentId;
    private EmploymentHistory.ChangeType changeType;
    private LocalDate effectiveDate;
    private String reason;
    private Long approvedById;
}
