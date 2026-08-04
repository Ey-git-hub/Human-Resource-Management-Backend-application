package com.HumanResourceManagement.Recruitment.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.HumanResourceManagement.Recruitment.dto.CandidateRequest;
import com.HumanResourceManagement.Recruitment.dto.CandidateResponse;

public interface CandidateServiceInterface {
    CandidateResponse createCandidate(CandidateRequest requestDto);

    CandidateResponse getCandidateById(Long id);

    Page<CandidateResponse> getAllCandidates(Pageable pageable);

    CandidateResponse updateCandidate(Long id, CandidateRequest requestDto);

    void deleteCandidate(Long id);
}
