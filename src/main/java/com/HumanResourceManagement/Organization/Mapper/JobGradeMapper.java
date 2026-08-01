package com.HumanResourceManagement.Organization.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Organization.DTO.JobGradeRequest;
import com.HumanResourceManagement.Organization.DTO.JobGradeResponse;
import com.HumanResourceManagement.Organization.Model.JobGrade;

@Component
public class JobGradeMapper {

    public JobGradeResponse toResponse(JobGrade jobGrade) {
        return JobGradeResponse.fromEntity(jobGrade);
    }

    public JobGrade toEntity(JobGradeRequest request) {
        return request.toEntity();
    }

    public void updateEntity(JobGrade existing, JobGradeRequest request) {
        existing.setName(request.getName());
        existing.setLevel(request.getLevel());
        existing.setDescription(request.getDescription());
        existing.setMinSalary(request.getMinSalary());
        existing.setMaxSalary(request.getMaxSalary());
    }
}
