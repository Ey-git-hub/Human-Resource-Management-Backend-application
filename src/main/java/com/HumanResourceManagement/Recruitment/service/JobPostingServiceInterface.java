package com.HumanResourceManagement.Recruitment.service;

import java.util.List;

import com.HumanResourceManagement.Recruitment.Model.JobPosting.Status;
import com.HumanResourceManagement.Recruitment.dto.JobPostingRequest;
import com.HumanResourceManagement.Recruitment.dto.JobPostingResponse;

public interface JobPostingServiceInterface {
    JobPostingResponse createJobPosting(JobPostingRequest requestDto);

    JobPostingResponse getJobPostingById(Long id);

    List<JobPostingResponse> getAllJobPostings();

    List<JobPostingResponse> getJobPostingsByDepartment(Long departmentId);

    List<JobPostingResponse> getJobPostingsByStatus(Status status);

    JobPostingResponse updateJobPostingStatus(Long id, Status status);

    JobPostingResponse updateJobPosting(Long id, JobPostingRequest requestDto);

    void deleteJobPosting(Long id);
}
