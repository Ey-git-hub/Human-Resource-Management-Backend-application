package com.HumanResourceManagement.Employee.DTO;

import lombok.Data;

import java.time.LocalDate;

import com.HumanResourceManagement.Employee.Model.EmployeeStatus;

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
