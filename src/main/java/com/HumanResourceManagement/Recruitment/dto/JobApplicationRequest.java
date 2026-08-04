package com.HumanResourceManagement.Recruitment.dto;

import com.HumanResourceManagement.Recruitment.Model.JobApplication.Status;
import com.HumanResourceManagement.Recruitment.Model.JobPosting;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JobApplicationRequest {

    @NotNull(message = "Candidate ID is required")
    private Long candidateId;

    @NotNull(message = "Job Posting ID is required")
    private Long jobPostingId;

    private LocalDate appliedDate = LocalDate.now();

    @NotNull(message = "Status is required")
    private Status status = Status.APPLIED;

    @Size(max = 3000, message = "Cover letter cannot exceed 3000 characters")
    private String coverLetter;

    private String resumeUrl;
    private String notes;
}