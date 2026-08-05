package com.HumanResourceManagement.Recruitment.dto;

import com.HumanResourceManagement.Recruitment.Model.Candidate.Source;
import com.HumanResourceManagement.Recruitment.Model.Candidate.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class CandidateResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String resumeUrl;
    private String coverLetterUrl;
    private String linkedInUrl;
    private Source source;
    private Status status;
}