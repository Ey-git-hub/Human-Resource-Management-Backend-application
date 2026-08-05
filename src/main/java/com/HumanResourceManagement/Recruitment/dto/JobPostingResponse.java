package com.HumanResourceManagement.Recruitment.dto;

import com.HumanResourceManagement.Recruitment.Model.JobPosting.EmploymentType;
import com.HumanResourceManagement.Recruitment.Model.JobPosting.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data

@NoArgsConstructor
public class JobPostingResponse {

    private Long id;
    private Long positionId;
    private String positionTitle;
    private Long departmentId;
    private String departmentName;
    private String title;
    private String description;
    private String requirements;
    private EmploymentType employmentType;
    private int numberOfOpenings;
    private LocalDate postedDate;
    private LocalDate closingDate;
    private Status status;
    private Long createdById;
    private String createdByName;
}