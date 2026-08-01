package com.HumanResourceManagement.Recruitment.repository;

import com.HumanResourceManagement.Recruitment.Model.JobApplication;
import com.HumanResourceManagement.Recruitment.Model.JobApplication.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    // Find all applications submitted by a specific Candidate
    List<JobApplication> findByCandidateId(Long candidateId);
    Page<JobApplication> findByCandidateId(Long candidateId, Pageable pageable);

    // Find all applications for a specific Job Posting
    List<JobApplication> findByJobPostingId(Long jobPostingId);
    Page<JobApplication> findByJobPostingId(Long jobPostingId, Pageable pageable);

    // Find applications by status (APPLIED, SHORTLISTED, INTERVIEW, OFFERED,
    // REJECTED, HIRED)
    List<JobApplication> findByStatus(Status status);
    Page<JobApplication> findByStatus(Status status, Pageable pageable);

    // Prevent duplicate applications by the same candidate for the same job posting
    boolean existsByCandidateIdAndJobPostingId(Long candidateId, Long jobPostingId);
}