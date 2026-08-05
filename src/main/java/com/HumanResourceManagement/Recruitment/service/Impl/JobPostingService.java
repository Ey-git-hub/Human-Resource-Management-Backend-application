package com.HumanResourceManagement.Recruitment.service.Impl;
import com.HumanResourceManagement.Recruitment.service.JobPostingServiceInterface;

import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.Employee.Repository.EmployeeRepository;
// import com.HumanResourceManagement.Employee.repository.EmployeeRepository;
import com.HumanResourceManagement.Organization.Model.Department;
import com.HumanResourceManagement.Organization.Model.Position;
import com.HumanResourceManagement.Organization.Repository.DepartmentRepository;
import com.HumanResourceManagement.Organization.Repository.PositionRepository;
// import com.HumanResourceManagement.Organization.repository.DepartmentRepository;
// import com.HumanResourceManagement.Organization.repository.PositionRepository;
import com.HumanResourceManagement.Recruitment.Mapper.JobPostingMapper;
import com.HumanResourceManagement.Recruitment.Model.JobPosting;
import com.HumanResourceManagement.Recruitment.Model.JobPosting.Status;
import com.HumanResourceManagement.Recruitment.dto.JobPostingRequest;
import com.HumanResourceManagement.Recruitment.dto.JobPostingResponse;
import com.HumanResourceManagement.Recruitment.repository.JobPostingRepository;
import com.HumanResourceManagement.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class JobPostingService implements JobPostingServiceInterface {

    private final JobPostingRepository jobPostingRepository;
    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final JobPostingMapper jobPostingMapper;

    @Override
    public JobPostingResponse createJobPosting(JobPostingRequest requestDto) {
        Position position = positionRepository.findById(requestDto.getPositionId())
                .orElseThrow(() -> ResourceNotFoundException.of("Position", requestDto.getPositionId()));

        Department department = departmentRepository.findById(requestDto.getDepartmentId())
                .orElseThrow(() -> ResourceNotFoundException.of("Department", requestDto.getDepartmentId()));

        Employee createdBy = employeeRepository.findById(requestDto.getCreatedById())
                .orElseThrow(() -> ResourceNotFoundException.of("Employee", requestDto.getCreatedById()));

        JobPosting jobPosting = jobPostingMapper.toEntity(requestDto, position, department, createdBy);
        JobPosting savedJobPosting = jobPostingRepository.save(jobPosting);

        return jobPostingMapper.toResponse(savedJobPosting);
    }

    @Override
    @Transactional(readOnly = true)
    public JobPostingResponse getJobPostingById(Long id) {
        JobPosting jobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("JobPosting", id));
        return jobPostingMapper.toResponse(jobPosting);
    }

    @Override
    @Transactional(readOnly = true)
    public List<JobPostingResponse> getAllJobPostings() {
        return jobPostingRepository.findAll().stream()
                .map(jobPostingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<JobPostingResponse> getJobPostingsByDepartment(Long departmentId) {
        return jobPostingRepository.findByDepartmentId(departmentId).stream()
                .map(jobPostingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<JobPostingResponse> getJobPostingsByStatus(Status status) {
        return jobPostingRepository.findByStatus(status).stream()
                .map(jobPostingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public JobPostingResponse updateJobPostingStatus(Long id, Status status) {
        JobPosting existingJobPosting = jobPostingRepository.findById(id)
                                .orElseThrow(() -> ResourceNotFoundException.of("JobPosting", id));

                existingJobPosting.setStatus(status);
                JobPosting updatedJobPosting = jobPostingRepository.save(existingJobPosting);
                return jobPostingMapper.toResponse(updatedJobPosting);
    }

    @Override
    public JobPostingResponse updateJobPosting(Long id, JobPostingRequest requestDto) {
        JobPosting existingJobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("JobPosting", id));

        Position position = positionRepository.findById(requestDto.getPositionId())
                .orElseThrow(() -> ResourceNotFoundException.of("Position", requestDto.getPositionId()));

        Department department = departmentRepository.findById(requestDto.getDepartmentId())
                .orElseThrow(() -> ResourceNotFoundException.of("Department", requestDto.getDepartmentId()));

        Employee createdBy = employeeRepository.findById(requestDto.getCreatedById())
                .orElseThrow(() -> ResourceNotFoundException.of("Employee", requestDto.getCreatedById()));

        jobPostingMapper.updateEntity(existingJobPosting, requestDto, position, department, createdBy);

        JobPosting updatedJobPosting = jobPostingRepository.save(existingJobPosting);
        return jobPostingMapper.toResponse(updatedJobPosting);
    }

    @Override
    public void deleteJobPosting(Long id) {
        if (!jobPostingRepository.existsById(id)) {
                        throw ResourceNotFoundException.of("JobPosting", id);
        }
        jobPostingRepository.deleteById(id);
    }
}