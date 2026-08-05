package com.HumanResourceManagement.Organization.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Organization.DTO.PositionRequest;
import com.HumanResourceManagement.Organization.DTO.PositionResponse;
import com.HumanResourceManagement.Organization.Model.Position;
import com.HumanResourceManagement.Organization.Model.Department;
import com.HumanResourceManagement.Organization.Model.JobGrade;
import com.HumanResourceManagement.shared.util.MapperUtils;

@Component
public class PositionMapper {

    public PositionResponse toResponse(Position position) {
        PositionResponse response = MapperUtils.map(position, PositionResponse.class);
        if (position.getDepartment() != null) {
            response.setDepartmentId(position.getDepartment().getId());
            response.setDepartmentName(position.getDepartment().getName());
        }
        if (position.getJobGrade() != null) {
            response.setJobGradeId(position.getJobGrade().getId());
            response.setJobGradeName(position.getJobGrade().getName());
        }
        return response;
    }

    public Position toEntity(PositionRequest request, Department department, JobGrade jobGrade) {
        Position position = MapperUtils.map(request, Position.class);
        position.setDepartment(department);
        position.setJobGrade(jobGrade);
        return position;
    }

    public void updateEntity(Position existing, PositionRequest request, Department department, JobGrade jobGrade) {
        MapperUtils.copy(request, existing);
        if (department != null) {
            existing.setDepartment(department);
        }
        existing.setJobGrade(jobGrade);
    }
}
