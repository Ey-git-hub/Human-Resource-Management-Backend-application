package com.HumanResourceManagement.Organization.Service;

import com.hrms.organization.dto.PositionRequestDto;
import com.hrms.organization.dto.PositionResponseDto;
import com.hrms.organization.entity.Position;
import com.hrms.organization.repository.PositionRepository;
import com.hrms.organization.service.PositionService;
import com.hrms.organization.repository.DepartmentRepository;
import com.hrms.organization.repository.JobGradeRepository;
import com.hrms.organization.entity.Department;
import com.hrms.organization.entity.JobGrade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PositionService {

        private final PositionRepository positionRepository;
        private final DepartmentRepository departmentRepository;
        private final JobGradeRepository jobGradeRepository;

        @Override
        public PositionResponseDto create(PositionRequestDto dto) {
                Position entity = Position.builder()
                                .title(dto.getTitle())
                                .code(dto.getCode())
                                .description(dto.getDescription())
                                .minSalary(dto.getMinSalary())
                                .maxSalary(dto.getMaxSalary())
                                .status(dto.getStatus())
                                .build();
                Department department = departmentRepository.findById(dto.getDepartmentId())
                                .orElseThrow(() -> new RuntimeException(
                                                "Department not found with id: " + dto.getDepartmentId()));
                entity.setDepartment(department);
                JobGrade jobGrade = jobGradeRepository.findById(dto.getJobGradeId())
                                .orElseThrow(() -> new RuntimeException(
                                                "JobGrade not found with id: " + dto.getJobGradeId()));
                entity.setJobGrade(jobGrade);
                Position saved = positionRepository.save(entity);
                return toResponseDto(saved);
        }

        @Override
        @Transactional(readOnly = true)
        public PositionResponseDto getById(Long id) {
                Position entity = positionRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Position not found with id: " + id));
                return toResponseDto(entity);
        }

        @Override
        @Transactional(readOnly = true)
        public List<PositionResponseDto> getAll() {
                return positionRepository.findAll().stream()
                                .map(this::toResponseDto)
                                .collect(Collectors.toList());
        }

        @Override
        public PositionResponseDto update(Long id, PositionRequestDto dto) {
                Position entity = positionRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Position not found with id: " + id));
                entity.setTitle(dto.getTitle());
                entity.setCode(dto.getCode());
                entity.setDescription(dto.getDescription());
                entity.setMinSalary(dto.getMinSalary());
                entity.setMaxSalary(dto.getMaxSalary());
                entity.setStatus(dto.getStatus());
                Department department = departmentRepository.findById(dto.getDepartmentId())
                                .orElseThrow(() -> new RuntimeException(
                                                "Department not found with id: " + dto.getDepartmentId()));
                entity.setDepartment(department);
                JobGrade jobGrade = jobGradeRepository.findById(dto.getJobGradeId())
                                .orElseThrow(() -> new RuntimeException(
                                                "JobGrade not found with id: " + dto.getJobGradeId()));
                entity.setJobGrade(jobGrade);
                Position saved = positionRepository.save(entity);
                return toResponseDto(saved);
        }

        @Override
        public void delete(Long id) {
                if (!positionRepository.existsById(id)) {
                        throw new RuntimeException("Position not found with id: " + id);
                }
                positionRepository.deleteById(id);
        }

        private PositionResponseDto toResponseDto(Position entity) {
                return PositionResponseDto.builder()
                                .id(entity.getId())
                                .title(entity.getTitle())
                                .code(entity.getCode())
                                .description(entity.getDescription())
                                .minSalary(entity.getMinSalary())
                                .maxSalary(entity.getMaxSalary())
                                .status(entity.getStatus())
                                .departmentId(entity.getDepartment() != null ? entity.getDepartment().getId() : null)
                                .jobGradeId(entity.getJobGrade() != null ? entity.getJobGrade().getId() : null)
                                .createdAt(entity.getCreatedAt())
                                .updatedAt(entity.getUpdatedAt())
                                .build();
        }
}
