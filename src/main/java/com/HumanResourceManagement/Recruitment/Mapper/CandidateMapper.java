package com.HumanResourceManagement.Recruitment.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Recruitment.Model.Candidate;
import com.HumanResourceManagement.Recruitment.dto.CandidateRequest;
import com.HumanResourceManagement.Recruitment.dto.CandidateResponse;

@Component
public class CandidateMapper {

    public CandidateResponse toResponse(Candidate candidate) {
        return CandidateResponse.fromEntity(candidate);
    }

    public Candidate toEntity(CandidateRequest request) {
        return request.toEntity();
    }

    public void updateEntity(Candidate existing, CandidateRequest request) {
        existing.setFirstName(request.getFirstName());
        existing.setLastName(request.getLastName());
        existing.setEmail(request.getEmail());
        existing.setPhone(request.getPhone());
        existing.setResumeUrl(request.getResumeUrl());
        existing.setCoverLetterUrl(request.getCoverLetterUrl());
        existing.setLinkedInUrl(request.getLinkedInUrl());
        existing.setSource(request.getSource());
        existing.setStatus(request.getStatus());
    }
}
