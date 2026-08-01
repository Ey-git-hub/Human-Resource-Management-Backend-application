package com.HumanResourceManagement.Recruitment.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Recruitment.Model.Candidate;
import com.HumanResourceManagement.Recruitment.Model.JobApplication;
import com.HumanResourceManagement.Recruitment.Model.JobPosting;
import com.HumanResourceManagement.Recruitment.dto.JobApplicationRequest;
import com.HumanResourceManagement.Recruitment.dto.JobApplicationResponse;

@Component
public class JobApplicationMapper {

    public JobApplicationResponse toResponse(JobApplication jobApplication) {
        return JobApplicationResponse.fromEntity(jobApplication);
    }

    public JobApplication toEntity(JobApplicationRequest request, Candidate candidate, JobPosting jobPosting) {
        return request.toEntity(candidate, jobPosting);
    }

    public void updateEntity(JobApplication existing, JobApplicationRequest request, Candidate candidate, JobPosting jobPosting) {
        existing.setCandidate(candidate);
        existing.setJobPosting(jobPosting);
        if (request.getAppliedDate() != null) {
            existing.setAppliedDate(request.getAppliedDate());
        }
        existing.setStatus(request.getStatus());
        existing.setCoverLetter(request.getCoverLetter());
        existing.setResumeUrl(request.getResumeUrl());
        existing.setNotes(request.getNotes());
    }
}
