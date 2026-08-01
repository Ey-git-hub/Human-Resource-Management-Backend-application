package com.HumanResourceManagement.Employee.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Employee.DTO.EmployeeRequest;
import com.HumanResourceManagement.Employee.DTO.EmployeeResponse;
import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.Employee.Model.EmployeeStatus;
import com.HumanResourceManagement.Organization.Model.Department;

/**
 * Centralizes Employee <-> DTO conversion so the service layer doesn't have
 * to manually copy every field back and forth.
 */
@Component
public class EmployeeMapper {

    public EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
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
        if (employee.getDepartment() != null) {
            response.setDepartmentId(employee.getDepartment().getId());
            response.setDepartment(employee.getDepartment().getName());
        }
        return response;
    }

    /** Builds a brand-new Employee entity from the create request. */
    public Employee toEntity(EmployeeRequest request, Department department) {
        Employee employee = new Employee();
        employee.setEmployeeCode(request.getEmployeeCode());
        employee.setFirstName(request.getFirstName());
        employee.setMiddleName(request.getMiddleName());
        employee.setLastName(request.getLastName());
        employee.setGender(request.getGender());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setNationalId(request.getNationalId());
        employee.setNationality(request.getNationality());
        employee.setMaritalStatus(request.getMaritalStatus());
        employee.setEmail(request.getEmail());
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setAddress(request.getAddress());
        employee.setPhotoUrl(request.getPhotoUrl());
        employee.setJobTitle(request.getJobTitle());
        employee.setSalary(request.getSalary());
        employee.setHireDate(request.getHireDate());
        employee.setStatus(request.getStatus() != null ? request.getStatus() : EmployeeStatus.ACTIVE);
        employee.setDepartment(department);
        return employee;
    }

    /** Applies the update request's fields onto an already-loaded entity. */
    public void updateEntity(Employee existing, EmployeeRequest request, Department department) {
        existing.setFirstName(request.getFirstName());
        existing.setLastName(request.getLastName());
        existing.setPhoneNumber(request.getPhoneNumber());
        existing.setJobTitle(request.getJobTitle());
        existing.setSalary(request.getSalary());
        existing.setHireDate(request.getHireDate());
        existing.setStatus(request.getStatus());
        existing.setEmail(request.getEmail());
        if (department != null) {
            existing.setDepartment(department);
        }
    }
}
