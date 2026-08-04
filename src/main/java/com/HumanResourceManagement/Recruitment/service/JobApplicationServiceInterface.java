package com.HumanResourceManagement.Recruitment.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.HumanResourceManagement.Recruitment.Model.JobApplication.Status;
import com.HumanResourceManagement.Recruitment.dto.JobApplicationRequest;
import com.HumanResourceManagement.Recruitment.dto.JobApplicationResponse;

public interface JobApplicationServiceInterface {
    JobApplicationResponse createJobApplication(JobApplicationRequest requestDto);

    JobApplicationResponse getJobApplicationById(Long id);

    Page<JobApplicationResponse> getAllJobApplications(Pageable pageable);

    Page<JobApplicationResponse> getApplicationsByCandidate(Long candidateId, Pageable pageable);

    Page<JobApplicationResponse> getApplicationsByJobPosting(Long jobPostingId, Pageable pageable);

    JobApplicationResponse updateApplicationStatus(Long id, Status status);

    JobApplicationResponse updateJobApplication(Long id, JobApplicationRequest requestDto);

    void deleteJobApplication(Long id);
}
