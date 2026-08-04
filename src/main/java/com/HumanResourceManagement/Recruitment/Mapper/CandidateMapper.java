package com.HumanResourceManagement.Recruitment.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Recruitment.Model.Candidate;
import com.HumanResourceManagement.Recruitment.dto.CandidateRequest;
import com.HumanResourceManagement.Recruitment.dto.CandidateResponse;
import com.HumanResourceManagement.shared.util.MapperUtils;

@Component
public class CandidateMapper {

    public CandidateResponse toResponse(Candidate candidate) {
        return MapperUtils.map(candidate, CandidateResponse.class);
    }

    public Candidate toEntity(CandidateRequest request) {
        return MapperUtils.map(request, Candidate.class);
    }

    public void updateEntity(Candidate existing, CandidateRequest request) {
        MapperUtils.copy(request, existing);
    }
}
