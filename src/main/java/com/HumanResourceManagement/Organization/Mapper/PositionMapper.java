package com.HumanResourceManagement.Organization.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Organization.DTO.PositionRequest;
import com.HumanResourceManagement.Organization.DTO.PositionResponse;
import com.HumanResourceManagement.Organization.Model.Position;
import com.HumanResourceManagement.Organization.Model.Department;
import com.HumanResourceManagement.Organization.Model.JobGrade;

@Component
public class PositionMapper {

    public PositionResponse toResponse(Position position) {
        return PositionResponse.fromEntity(position);
    }

    public Position toEntity(PositionRequest request, Department department, JobGrade jobGrade) {
        return request.toEntity(department, jobGrade);
    }

    public void updateEntity(Position existing, PositionRequest request, Department department, JobGrade jobGrade) {
        if (department != null) {
            existing.setDepartment(department);
        }
        existing.setJobGrade(jobGrade);
        existing.setTitle(request.getTitle());
        existing.setCode(request.getCode());
        existing.setDescription(request.getDescription());
        existing.setMinSalary(request.getMinSalary());
        existing.setMaxSalary(request.getMaxSalary());
        existing.setStatus(request.getStatus());
    }
}
