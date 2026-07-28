package com.HumanResourceManagement.Recruitment.service;

import com.HumanResourceManagement.Recruitment.Model.Candidate;
import com.HumanResourceManagement.Recruitment.dto.CandidateRequestDto;
import com.HumanResourceManagement.Recruitment.dto.CandidateResponseDto;
import com.HumanResourceManagement.Recruitment.repository.CandidateRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateResponseDto createCandidate(CandidateRequestDto requestDto) {
        Candidate candidate = requestDto.toEntity();
        Candidate savedCandidate = candidateRepository.save(candidate);
        return CandidateResponseDto.fromEntity(savedCandidate);
    }

    @Transactional(readOnly = true)
    public CandidateResponseDto getCandidateById(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Candidate not found with ID: " + id));
        return CandidateResponseDto.fromEntity(candidate);
    }

    @Transactional(readOnly = true)
    public List<CandidateResponseDto> getAllCandidates() {
        return candidateRepository.findAll().stream()
                .map(CandidateResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public CandidateResponseDto updateCandidate(Long id, CandidateRequestDto requestDto) {
        Candidate existingCandidate = candidateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Candidate not found with ID: " + id));

        existingCandidate.setFirstName(requestDto.getFirstName());
        existingCandidate.setLastName(requestDto.getLastName());
        existingCandidate.setEmail(requestDto.getEmail());
        existingCandidate.setPhone(requestDto.getPhone());
        existingCandidate.setResumeUrl(requestDto.getResumeUrl());
        existingCandidate.setCoverLetterUrl(requestDto.getCoverLetterUrl());
        existingCandidate.setLinkedInUrl(requestDto.getLinkedInUrl());
        existingCandidate.setSource(requestDto.getSource());
        existingCandidate.setStatus(requestDto.getStatus());

        Candidate updatedCandidate = candidateRepository.save(existingCandidate);
        return CandidateResponseDto.fromEntity(updatedCandidate);
    }

    public void deleteCandidate(Long id) {
        if (!candidateRepository.existsById(id)) {
            throw new EntityNotFoundException("Candidate not found with ID: " + id);
        }
        candidateRepository.deleteById(id);
    }
}