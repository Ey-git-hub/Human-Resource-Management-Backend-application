package com.HumanResourceManagement.Organization.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HumanResourceManagement.Organization.DTO.JobGradeRequest;
import com.HumanResourceManagement.Organization.DTO.JobGradeResponse;
import com.HumanResourceManagement.Organization.Model.JobGrade;
import com.HumanResourceManagement.Organization.Repository.JobGradeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class JobGradeService {

    private final JobGradeRepository jobGradeRepository;

    public JobGradeResponse create(JobGradeRequest dto) {
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

    @Transactional(readOnly = true)
    public JobGradeResponse getById(Long id) {
        JobGrade entity = jobGradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobGrade not found with id: " + id));
        return toResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<JobGradeResponse> getAll() {
        return jobGradeRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public JobGradeResponse update(Long id, JobGradeRequest dto) {
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

    private JobGradeResponse toResponseDto(JobGrade entity) {
        return JobGradeResponse.builder()
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
