package com.HumanResourceManagement.Recruitment.service;

import com.HumanResourceManagement.Recruitment.Mapper.JobApplicationMapper;
import com.HumanResourceManagement.Recruitment.Model.Candidate;
import com.HumanResourceManagement.Recruitment.Model.JobApplication;
import com.HumanResourceManagement.Recruitment.Model.JobApplication.Status;
import com.HumanResourceManagement.Recruitment.Model.JobPosting;
import com.HumanResourceManagement.Recruitment.dto.JobApplicationRequest;
import com.HumanResourceManagement.Recruitment.dto.JobApplicationResponse;
import com.HumanResourceManagement.Recruitment.Mapper.JobApplicationMapper;
import com.HumanResourceManagement.Recruitment.repository.CandidateRepository;
import com.HumanResourceManagement.Recruitment.repository.JobApplicationRepository;
import com.HumanResourceManagement.Recruitment.repository.JobPostingRepository;
import com.HumanResourceManagement.shared.exception.DuplicateResourceException;
import com.HumanResourceManagement.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final CandidateRepository candidateRepository;
    private final JobPostingRepository jobPostingRepository;
    private final JobApplicationMapper jobApplicationMapper;

    public JobApplicationResponse createJobApplication(JobApplicationRequest requestDto) {
        if (jobApplicationRepository.existsByCandidateIdAndJobPostingId(
                requestDto.getCandidateId(), requestDto.getJobPostingId())) {
            throw new DuplicateResourceException("Candidate has already applied for this job posting.");
        }

        Candidate candidate = candidateRepository.findById(requestDto.getCandidateId())
                .orElseThrow(() -> ResourceNotFoundException.of("Candidate", requestDto.getCandidateId()));

        JobPosting jobPosting = jobPostingRepository.findById(requestDto.getJobPostingId())
                .orElseThrow(() -> ResourceNotFoundException.of("JobPosting", requestDto.getJobPostingId()));

        JobApplication jobApplication = jobApplicationMapper.toEntity(requestDto, candidate, jobPosting);
        JobApplication savedApplication = jobApplicationRepository.save(jobApplication);

        return jobApplicationMapper.toResponse(savedApplication);
    }

    @Transactional(readOnly = true)
    public JobApplicationResponse getJobApplicationById(Long id) {
        JobApplication jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("JobApplication", id));
        return jobApplicationMapper.toResponse(jobApplication);
    }

    @Transactional(readOnly = true)
    public Page<JobApplicationResponse> getAllJobApplications(Pageable pageable) {
        return jobApplicationRepository.findAll(pageable).map(jobApplicationMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<JobApplicationResponse> getApplicationsByCandidate(Long candidateId, Pageable pageable) {
        return jobApplicationRepository.findByCandidateId(candidateId, pageable)
                .map(jobApplicationMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<JobApplicationResponse> getApplicationsByJobPosting(Long jobPostingId, Pageable pageable) {
        return jobApplicationRepository.findByJobPostingId(jobPostingId, pageable)
                .map(jobApplicationMapper::toResponse);
    }

    public JobApplicationResponse updateApplicationStatus(Long id, Status status) {
        JobApplication existingApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("JobApplication", id));

        existingApplication.setStatus(status);
        JobApplication updatedApplication = jobApplicationRepository.save(existingApplication);
        return jobApplicationMapper.toResponse(updatedApplication);
    }

    public JobApplicationResponse updateJobApplication(Long id, JobApplicationRequest requestDto) {
        JobApplication existingApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("JobApplication", id));

        Candidate candidate = candidateRepository.findById(requestDto.getCandidateId())
                .orElseThrow(() -> ResourceNotFoundException.of("Candidate", requestDto.getCandidateId()));

        JobPosting jobPosting = jobPostingRepository.findById(requestDto.getJobPostingId())
                .orElseThrow(() -> ResourceNotFoundException.of("JobPosting", requestDto.getJobPostingId()));

        existingApplication.setCandidate(candidate);
        existingApplication.setJobPosting(jobPosting);
        if (requestDto.getAppliedDate() != null) {
            existingApplication.setAppliedDate(requestDto.getAppliedDate());
        }
        existingApplication.setStatus(requestDto.getStatus());
        existingApplication.setCoverLetter(requestDto.getCoverLetter());
        existingApplication.setResumeUrl(requestDto.getResumeUrl());
        existingApplication.setNotes(requestDto.getNotes());

        JobApplication updatedApplication = jobApplicationRepository.save(existingApplication);
        return jobApplicationMapper.toResponse(updatedApplication);
    }

    public void deleteJobApplication(Long id) {
        if (!jobApplicationRepository.existsById(id)) {
            throw ResourceNotFoundException.of("JobApplication", id);
        }
        jobApplicationRepository.deleteById(id);
    }
}