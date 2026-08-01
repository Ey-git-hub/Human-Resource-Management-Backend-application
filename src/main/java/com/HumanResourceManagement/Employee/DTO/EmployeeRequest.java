package com.HumanResourceManagement.Employee.DTO;

import lombok.Data;

import java.time.LocalDate;

import com.HumanResourceManagement.Employee.Model.EmployeeStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

@Data
public class EmployeeRequest {

    @NotBlank(message = "Employee code is required")
    private String employeeCode;

    private String middleName;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String phoneNumber;

    @NotNull(message = "Hire date is required")
    @PastOrPresent(message = "Hire date cannot be in the future")
    private LocalDate hireDate;

    @NotBlank(message = "Job title is required")
    private String JobTitle;

    @NotNull(message = "Salary is required")
    @PositiveOrZero(message = "Salary must be non-negative")
    private Double salary;

    private Long DepartmentId;

    private EmployeeStatus status;

    @NotBlank(message = "Gender is required")
    private String gender;

    private String nationalId;
    private String nationality;
    private String maritalStatus;
    private String address;
    private String photoUrl;

}
