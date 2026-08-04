package com.HumanResourceManagement.Recruitment.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Recruitment.Model.Candidate;
import com.HumanResourceManagement.Recruitment.Model.JobApplication;
import com.HumanResourceManagement.Recruitment.Model.JobPosting;
import com.HumanResourceManagement.Recruitment.dto.JobApplicationRequest;
import com.HumanResourceManagement.Recruitment.dto.JobApplicationResponse;
import com.HumanResourceManagement.shared.util.MapperUtils;

@Component
public class JobApplicationMapper {

    public JobApplicationResponse toResponse(JobApplication jobApplication) {
        JobApplicationResponse response = MapperUtils.map(jobApplication, JobApplicationResponse.class);
        if (jobApplication.getCandidate() != null) {
            response.setCandidateId(jobApplication.getCandidate().getId());
            response.setCandidateName(jobApplication.getCandidate().getFirstName() + " " + jobApplication.getCandidate().getLastName());
            response.setCandidateEmail(jobApplication.getCandidate().getEmail());
        }
        if (jobApplication.getJobPosting() != null) {
            response.setJobPostingId(jobApplication.getJobPosting().getId());
            response.setJobTitle(jobApplication.getJobPosting().getTitle());
        }
        return response;
    }

    public JobApplication toEntity(JobApplicationRequest request, Candidate candidate, JobPosting jobPosting) {
        JobApplication jobApplication = MapperUtils.map(request, JobApplication.class);
        jobApplication.setCandidate(candidate);
        jobApplication.setJobPosting(jobPosting);
        if (request.getAppliedDate() != null) {
            jobApplication.setAppliedDate(request.getAppliedDate());
        }
        return jobApplication;
    }

    public void updateEntity(JobApplication existing, JobApplicationRequest request, Candidate candidate, JobPosting jobPosting) {
        MapperUtils.copy(request, existing);
        existing.setCandidate(candidate);
        existing.setJobPosting(jobPosting);
        if (request.getAppliedDate() != null) {
            existing.setAppliedDate(request.getAppliedDate());
        }
    }
}
