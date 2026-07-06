package com.HumanResourceManagement.application.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class EmployeeResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private String JobTile;
    private Double salary;
    private Long DepartmentId;


}
