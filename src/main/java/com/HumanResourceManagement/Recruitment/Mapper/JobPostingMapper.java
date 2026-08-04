package com.HumanResourceManagement.Recruitment.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.Organization.Model.Department;
import com.HumanResourceManagement.Organization.Model.Position;
import com.HumanResourceManagement.Recruitment.Model.JobPosting;
import com.HumanResourceManagement.Recruitment.dto.JobPostingRequest;
import com.HumanResourceManagement.Recruitment.dto.JobPostingResponse;
import com.HumanResourceManagement.shared.util.MapperUtils;

@Component
public class JobPostingMapper {

    public JobPostingResponse toResponse(JobPosting jobPosting) {
        JobPostingResponse response = MapperUtils.map(jobPosting, JobPostingResponse.class);
        if (jobPosting.getPosition() != null) {
            response.setPositionId(jobPosting.getPosition().getId());
            response.setPositionTitle(jobPosting.getPosition().getTitle());
        }
        if (jobPosting.getDepartment() != null) {
            response.setDepartmentId(jobPosting.getDepartment().getId());
            response.setDepartmentName(jobPosting.getDepartment().getName());
        }
        if (jobPosting.getCreatedBy() != null) {
            response.setCreatedById(jobPosting.getCreatedBy().getId());
            response.setCreatedByName(jobPosting.getCreatedBy().getFirstName() + " " + jobPosting.getCreatedBy().getLastName());
        }
        return response;
    }

    public JobPosting toEntity(JobPostingRequest request, Position position, Department department, Employee createdBy) {
        JobPosting jobPosting = MapperUtils.map(request, JobPosting.class);
        jobPosting.setPosition(position);
        jobPosting.setDepartment(department);
        jobPosting.setCreatedBy(createdBy);
        if (request.getPostedDate() != null) {
            jobPosting.setPostedDate(request.getPostedDate());
        }
        return jobPosting;
    }

    public void updateEntity(JobPosting existing, JobPostingRequest request, Position position, Department department, Employee createdBy) {
        MapperUtils.copy(request, existing);
        existing.setPosition(position);
        existing.setDepartment(department);
        existing.setCreatedBy(createdBy);
        if (request.getPostedDate() != null) {
            existing.setPostedDate(request.getPostedDate());
        }
    }
}
