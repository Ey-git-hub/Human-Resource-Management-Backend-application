package com.HumanResourceManagement.Organization.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Organization.DTO.JobGradeRequest;
import com.HumanResourceManagement.Organization.DTO.JobGradeResponse;
import com.HumanResourceManagement.Organization.Model.JobGrade;
import com.HumanResourceManagement.shared.util.MapperUtils;

@Component
public class JobGradeMapper {

    public JobGradeResponse toResponse(JobGrade jobGrade) {
        return MapperUtils.map(jobGrade, JobGradeResponse.class);
    }

    public JobGrade toEntity(JobGradeRequest request) {
        return MapperUtils.map(request, JobGrade.class);
    }

    public void updateEntity(JobGrade existing, JobGradeRequest request) {
        MapperUtils.copy(request, existing);
    }
}
