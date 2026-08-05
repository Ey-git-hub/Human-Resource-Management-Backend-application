package com.HumanResourceManagement.Employee.DTO;

import com.HumanResourceManagement.Employee.Model.EmploymentContract;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data

@NoArgsConstructor
public class EmploymentContractResponse {
    private Long id;
    private Long employeeId;
    private EmploymentContract.ContractType contractType;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal salary;
    private String currency;
    private int workHoursPerWeek;
    private EmploymentContract.Status status;
    private LocalDate signedDate;
    private String documentUrl;
}
