package com.HumanResourceManagement.Organization.Service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.HumanResourceManagement.Organization.DTO.JobGradeRequest;
import com.HumanResourceManagement.Organization.DTO.JobGradeResponse;

public interface JobGradeServiceInterface {
    JobGradeResponse createJobGrade(JobGradeRequest requestDto);

    Optional<JobGradeResponse> getJobGradeById(Long id);

    Page<JobGradeResponse> getAllJobGrades(Pageable pageable);

    JobGradeResponse updateJobGrade(Long id, JobGradeRequest requestDto);

    void deleteJobGrade(Long id);
}
