package com.HumanResourceManagement.Employee.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.HumanResourceManagement.Employee.Model.EmployeeStatus;

@Data
public class EmployeeResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private String JobTile;
    private Double salary;
    private Long departmentId;
    private String department;
    private EmployeeStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
