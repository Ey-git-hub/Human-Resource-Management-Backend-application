package com.HumanResourceManagement.application.dto;

import com.HumanResourceManagement.application.model.EmployeeStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private String JobTitle;
    private Double salary;
    private Long DepartmentId;
    private EmployeeStatus status;

}
