package com.HumanResourceManagement.Employee.DTO;

import com.HumanResourceManagement.Employee.Model.EmploymentContract;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmploymentContractRequest {
    @NotNull
    private Long employeeId;

    @NotNull
    private EmploymentContract.ContractType contractType;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull
    private BigDecimal salary;

    @NotNull
    private String currency;

    private int workHoursPerWeek;

    private EmploymentContract.Status status;

    private LocalDate signedDate;

    private String documentUrl;
}
