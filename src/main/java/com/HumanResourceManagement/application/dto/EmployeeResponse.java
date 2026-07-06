package com.HumanResourceManagement.application.dto;

import com.HumanResourceManagement.application.model.Employee;
import com.HumanResourceManagement.application.model.EmployeeStatus;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

public static EmployeeResponse fromEmployee(Employee employee){
EmployeeResponse response=new EmployeeResponse();
response.setId(employee.getId());
response.setFirstName(employee.getFirstName());
response.setLastName(employee.getLastName());
response.setEmail(employee.getEmail());
response.setPhoneNumber(employee.getPhoneNumber());
response.setHireDate(employee.getHireDate());
response.setJobTile(employee.getJobTitle());
response.setSalary(employee.getSalary());
response.setStatus(employee.getStatus());
response.setCreatedAt(employee.getCreatedAt());
response.setUpdatedAt(employee.getUpdatedAt());
//after relation ship with tables
if (employee.getDepartment()!=null){
    response.setDepartmentId(employee.getDepartment().getId());
    response.setDepartment(employee.getDepartment().getName());
}
    return response;
}}
