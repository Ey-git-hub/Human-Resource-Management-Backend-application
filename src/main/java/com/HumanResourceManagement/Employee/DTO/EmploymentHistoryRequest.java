package com.HumanResourceManagement.Employee.DTO;

import com.HumanResourceManagement.Employee.Model.EmploymentHistory;
import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmploymentHistoryRequest {
    @NotNull
    private Long employeeId;

    private Long previousPositionId;
    @NotNull
    private Long newPositionId;

    private Long previousDepartmentId;
    @NotNull
    private Long newDepartmentId;

    @NotNull
    private EmploymentHistory.ChangeType changeType;

    @NotNull
    private LocalDate effectiveDate;

    private String reason;

    private Long approvedById;
}
