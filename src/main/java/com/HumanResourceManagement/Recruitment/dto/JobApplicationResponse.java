package com.HumanResourceManagement.Recruitment.dto;

import com.HumanResourceManagement.Recruitment.Model.JobApplication.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class JobApplicationResponse {

    private Long id;
    private Long candidateId;
    private String candidateName;
    private String candidateEmail;
    private Long jobPostingId;
    private String jobTitle;
    private LocalDate appliedDate;
    private Status status;
    private String coverLetter;
    private String resumeUrl;
    private String notes;
}