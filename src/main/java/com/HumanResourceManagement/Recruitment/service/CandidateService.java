package com.HumanResourceManagement.Recruitment.service;

import com.HumanResourceManagement.Recruitment.Mapper.CandidateMapper;
import com.HumanResourceManagement.Recruitment.Model.Candidate;
import com.HumanResourceManagement.Recruitment.dto.CandidateRequest;
import com.HumanResourceManagement.Recruitment.dto.CandidateResponse;
import com.HumanResourceManagement.Recruitment.repository.CandidateRepository;
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
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;

    public CandidateResponse createCandidate(CandidateRequest requestDto) {
        if (candidateRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateResourceException("Candidate already exists with email: " + requestDto.getEmail());
        }

        Candidate candidate = candidateMapper.toEntity(requestDto);
        Candidate savedCandidate = candidateRepository.save(candidate);
        return candidateMapper.toResponse(savedCandidate);
    }

    @Transactional(readOnly = true)
    public CandidateResponse getCandidateById(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Candidate", id));
        return candidateMapper.toResponse(candidate);
    }

    @Transactional(readOnly = true)
    public Page<CandidateResponse> getAllCandidates(Pageable pageable) {
        return candidateRepository.findAll(pageable).map(candidateMapper::toResponse);
    }

    public CandidateResponse updateCandidate(Long id, CandidateRequest requestDto) {
        Candidate existingCandidate = candidateRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Candidate", id));

        candidateMapper.updateEntity(existingCandidate, requestDto);
        Candidate updatedCandidate = candidateRepository.save(existingCandidate);
        return candidateMapper.toResponse(updatedCandidate);
    }

    public void deleteCandidate(Long id) {
        if (!candidateRepository.existsById(id)) {
            throw ResourceNotFoundException.of("Candidate", id);
        }
        candidateRepository.deleteById(id);
    }
}