package com.HumanResourceManagement.Organization.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HumanResourceManagement.Organization.DTO.PositionRequest;
import com.HumanResourceManagement.Organization.DTO.PositionResponse;
import com.HumanResourceManagement.Organization.Model.Department;
import com.HumanResourceManagement.Organization.Model.JobGrade;
import com.HumanResourceManagement.Organization.Model.Position;
import com.HumanResourceManagement.Organization.Repository.DepartmentRepository;
import com.HumanResourceManagement.Organization.Repository.JobGradeRepository;
import com.HumanResourceManagement.Organization.Repository.PositionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PositionService {

        private final PositionRepository positionRepository;
        private final DepartmentRepository departmentRepository;
        private final JobGradeRepository jobGradeRepository;

        public PositionResponse create(PositionRequest dto) {
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

        @Transactional(readOnly = true)
        public PositionResponse getById(Long id) {
                Position entity = positionRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Position not found with id: " + id));
                return toResponseDto(entity);
        }

        @Transactional(readOnly = true)
        public List<PositionResponse> getAll() {
                return positionRepository.findAll().stream()
                                .map(this::toResponseDto)
                                .collect(Collectors.toList());
        }

        @Override
        public PositionResponse update(Long id, PositionRequest dto) {
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

        public void delete(Long id) {
                if (!positionRepository.existsById(id)) {
                        throw new RuntimeException("Position not found with id: " + id);
                }
                positionRepository.deleteById(id);
        }

        // private PositionResponse toResponseDto(Position entity) {
        // return PositionResponseDto.builder()
        // .id(entity.getId())
        // .title(entity.getTitle())
        // .code(entity.getCode())
        // .description(entity.getDescription())
        // .minSalary(entity.getMinSalary())
        // .maxSalary(entity.getMaxSalary())
        // .status(entity.getStatus())
        // .departmentId(entity.getDepartment() != null ? entity.getDepartment().getId()
        // : null)
        // .jobGradeId(entity.getJobGrade() != null ? entity.getJobGrade().getId() :
        // null)
        // .createdAt(entity.getCreatedAt())
        // .updatedAt(entity.getUpdatedAt())
        // .build();
        // }
}
