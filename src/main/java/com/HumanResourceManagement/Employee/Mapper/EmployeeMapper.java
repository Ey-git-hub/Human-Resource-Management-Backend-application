package com.HumanResourceManagement.Employee.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Employee.DTO.EmployeeRequest;
import com.HumanResourceManagement.Employee.DTO.EmployeeResponse;
import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.Employee.Model.EmployeeStatus;
import com.HumanResourceManagement.Organization.Model.Department;
import com.HumanResourceManagement.shared.util.MapperUtils;

/**
 * Centralizes Employee <-> DTO conversion so the service layer doesn't have
 * to manually copy every field back and forth.
 */
@Component
public class EmployeeMapper {

    public EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse response = MapperUtils.map(employee, EmployeeResponse.class);
        response.setJobTile(employee.getJobTitle());
        if (employee.getDepartment() != null) {
            response.setDepartmentId(employee.getDepartment().getId());
            response.setDepartment(employee.getDepartment().getName());
        }
        return response;
    }

    /** Builds a brand-new Employee entity from the create request. */
    public Employee toEntity(EmployeeRequest request, Department department) {
        Employee employee = MapperUtils.map(request, Employee.class);
        employee.setStatus(request.getStatus() != null ? request.getStatus() : EmployeeStatus.ACTIVE);
        employee.setDepartment(department);
        return employee;
    }

    /** Applies the update request's fields onto an already-loaded entity. */
    public void updateEntity(Employee existing, EmployeeRequest request, Department department) {
        MapperUtils.copy(request, existing);
        if (request.getStatus() != null) {
            existing.setStatus(request.getStatus());
        }
        if (department != null) {
            existing.setDepartment(department);
        }
    }
}
