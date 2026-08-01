package com.HumanResourceManagement.Organization.Service;

import com.HumanResourceManagement.Organization.Model.JobGrade;
import com.HumanResourceManagement.Organization.DTO.JobGradeRequest;
import com.HumanResourceManagement.Organization.DTO.JobGradeResponse;
import com.HumanResourceManagement.Organization.Mapper.JobGradeMapper;
import com.HumanResourceManagement.Organization.Repository.JobGradeRepository;
import com.HumanResourceManagement.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class JobGradeService {

    private final JobGradeRepository jobGradeRepository;
    private final JobGradeMapper jobGradeMapper;

    public JobGradeResponse createJobGrade(JobGradeRequest requestDto) {
        JobGrade jobGrade = jobGradeMapper.toEntity(requestDto);
        JobGrade savedJobGrade = jobGradeRepository.save(jobGrade);
        return jobGradeMapper.toResponse(savedJobGrade);
    }

    @Transactional(readOnly = true)
    public Optional<JobGradeResponse> getJobGradeById(Long id) {
        return jobGradeRepository.findById(id).map(jobGradeMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<JobGradeResponse> getAllJobGrades(Pageable pageable) {
        return jobGradeRepository.findAll(pageable).map(jobGradeMapper::toResponse);
    }

    public JobGradeResponse updateJobGrade(Long id, JobGradeRequest requestDto) {
        JobGrade existingJobGrade = jobGradeRepository.findById(id)
            .orElseThrow(() -> ResourceNotFoundException.of("JobGrade", id));

        jobGradeMapper.updateEntity(existingJobGrade, requestDto);

        JobGrade updatedJobGrade = jobGradeRepository.save(existingJobGrade);
        return jobGradeMapper.toResponse(updatedJobGrade);
    }

    public void deleteJobGrade(Long id) {
        if (!jobGradeRepository.existsById(id)) {
            throw ResourceNotFoundException.of("JobGrade", id);
        }
        jobGradeRepository.deleteById(id);
    }
}