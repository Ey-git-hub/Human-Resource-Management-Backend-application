package com.HumanResourceManagement.Organization.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Organization.DTO.DepartmentRequest;
import com.HumanResourceManagement.Organization.DTO.DepartmentResponse;
import com.HumanResourceManagement.Organization.Model.Department;
import com.HumanResourceManagement.shared.util.MapperUtils;

@Component
public class DepartmentMapper {

    public DepartmentResponse toResponse(Department department) {
        DepartmentResponse response = MapperUtils.map(department, DepartmentResponse.class);
        if (department.getManager() != null) {
            response.setManagerId(department.getManager().getId());
            response.setManagerName(department.getManager().getFirstName() + " " + department.getManager().getLastName());
        }
        return response;
    }

    public Department toEntity(DepartmentRequest request) {
        return MapperUtils.map(request, Department.class);
    }

    public void updateEntity(Department existing, DepartmentRequest request) {
        MapperUtils.copy(request, existing);
    }
}
