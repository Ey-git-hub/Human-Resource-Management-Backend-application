package com.hrms.organization.service.impl;

import com.hrms.organization.dto.JobGradeRequestDto;
import com.hrms.organization.dto.JobGradeResponseDto;
import com.hrms.organization.entity.JobGrade;
import com.hrms.organization.repository.JobGradeRepository;
import com.hrms.organization.service.JobGradeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class JobGradeService {

    private final JobGradeRepository jobGradeRepository;

    @Override
    public JobGradeResponseDto create(JobGradeRequestDto dto) {
        JobGrade entity = JobGrade.builder()
                .name(dto.getName())
                .level(dto.getLevel())
                .description(dto.getDescription())
                .minSalary(dto.getMinSalary())
                .maxSalary(dto.getMaxSalary())
                .build();

        JobGrade saved = jobGradeRepository.save(entity);
        return toResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public JobGradeResponseDto getById(Long id) {
        JobGrade entity = jobGradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobGrade not found with id: " + id));
        return toResponseDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<JobGradeResponseDto> getAll() {
        return jobGradeRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public JobGradeResponseDto update(Long id, JobGradeRequestDto dto) {
        JobGrade entity = jobGradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobGrade not found with id: " + id));
        entity.setName(dto.getName());
        entity.setLevel(dto.getLevel());
        entity.setDescription(dto.getDescription());
        entity.setMinSalary(dto.getMinSalary());
        entity.setMaxSalary(dto.getMaxSalary());

        JobGrade saved = jobGradeRepository.save(entity);
        return toResponseDto(saved);
    }

    @Override
    public void delete(Long id) {
        if (!jobGradeRepository.existsById(id)) {
            throw new RuntimeException("JobGrade not found with id: " + id);
        }
        jobGradeRepository.deleteById(id);
    }

    private JobGradeResponseDto toResponseDto(JobGrade entity) {
        return JobGradeResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .level(entity.getLevel())
                .description(entity.getDescription())
                .minSalary(entity.getMinSalary())
                .maxSalary(entity.getMaxSalary())

                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
