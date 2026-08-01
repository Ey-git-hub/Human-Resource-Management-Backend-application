package com.HumanResourceManagement.Recruitment.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.Organization.Model.Department;
import com.HumanResourceManagement.Organization.Model.Position;
import com.HumanResourceManagement.Recruitment.Model.JobPosting;
import com.HumanResourceManagement.Recruitment.dto.JobPostingRequest;
import com.HumanResourceManagement.Recruitment.dto.JobPostingResponse;

@Component
public class JobPostingMapper {

    public JobPostingResponse toResponse(JobPosting jobPosting) {
        return JobPostingResponse.fromEntity(jobPosting);
    }

    public JobPosting toEntity(JobPostingRequest request, Position position, Department department, Employee createdBy) {
        return request.toEntity(position, department, createdBy);
    }

    public void updateEntity(JobPosting existing, JobPostingRequest request, Position position, Department department, Employee createdBy) {
        existing.setPosition(position);
        existing.setDepartment(department);
        existing.setTitle(request.getTitle());
        existing.setDescription(request.getDescription());
        existing.setRequirements(request.getRequirements());
        existing.setEmploymentType(request.getEmploymentType());
        existing.setNumberOfOpenings(request.getNumberOfOpenings());
        if (request.getPostedDate() != null) {
            existing.setPostedDate(request.getPostedDate());
        }
        existing.setClosingDate(request.getClosingDate());
        existing.setStatus(request.getStatus());
        existing.setCreatedBy(createdBy);
    }
}
