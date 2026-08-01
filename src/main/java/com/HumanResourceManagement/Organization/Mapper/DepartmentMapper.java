package com.HumanResourceManagement.Organization.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Organization.DTO.DepartmentRequest;
import com.HumanResourceManagement.Organization.DTO.DepartmentResponse;
import com.HumanResourceManagement.Organization.Model.Department;

@Component
public class DepartmentMapper {

    public DepartmentResponse toResponse(Department department) {
        DepartmentResponse response = new DepartmentResponse();
        response.setId(department.getId());
        response.setName(department.getName());
        response.setDescription(department.getDescription());
        response.setCreatedAt(department.getCreatedAt());
        response.setUpdatedAt(department.getUpdatedAt());
        if (department.getManager() != null) {
            response.setManagerId(department.getManager().getId());
            response.setManagerName(
                    department.getManager().getFirstName() + " " + department.getManager().getLastName());
        }
        return response;
    }

    public Department toEntity(DepartmentRequest request) {
        Department department = new Department();
        department.setName(request.getName());
        department.setDescription(request.getDescription());
        return department;
    }

    public void updateEntity(Department existing, DepartmentRequest request) {
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
    }
}
