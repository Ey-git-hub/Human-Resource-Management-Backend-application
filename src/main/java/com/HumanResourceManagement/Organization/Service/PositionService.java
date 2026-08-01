package com.HumanResourceManagement.Organization.Service;

import com.HumanResourceManagement.Organization.Model.Department;
import com.HumanResourceManagement.Organization.Model.JobGrade;
import com.HumanResourceManagement.Organization.Model.Position;
import com.HumanResourceManagement.Organization.DTO.PositionRequest;
import com.HumanResourceManagement.Organization.DTO.PositionResponse;
import com.HumanResourceManagement.Organization.Mapper.PositionMapper;
import com.HumanResourceManagement.Organization.Repository.DepartmentRepository;
import com.HumanResourceManagement.Organization.Repository.JobGradeRepository;
import com.HumanResourceManagement.Organization.Repository.PositionRepository;
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
public class PositionService {

        private final PositionRepository positionRepository;
        private final DepartmentRepository departmentRepository;
        private final JobGradeRepository jobGradeRepository;
        private final PositionMapper positionMapper;

        public PositionResponse createPosition(PositionRequest requestDto) {
                Department department = departmentRepository.findById(requestDto.getDepartmentId())
                                .orElseThrow(() -> ResourceNotFoundException.of("Department", requestDto.getDepartmentId()));

                JobGrade jobGrade = null;
                if (requestDto.getJobGradeId() != null) {
                        jobGrade = jobGradeRepository.findById(requestDto.getJobGradeId())
                                        .orElseThrow(() -> ResourceNotFoundException.of("JobGrade", requestDto.getJobGradeId()));
                }

                Position position = positionMapper.toEntity(requestDto, department, jobGrade);
                Position savedPosition = positionRepository.save(position);

                return positionMapper.toResponse(savedPosition);
        }

        @Transactional(readOnly = true)
        public Optional<PositionResponse> getPositionById(Long id) {
                return positionRepository.findById(id).map(positionMapper::toResponse);
        }

        @Transactional(readOnly = true)
        public Page<PositionResponse> getAllPositions(Pageable pageable) {
                return positionRepository.findAll(pageable).map(positionMapper::toResponse);
        }

        @Transactional(readOnly = true)
        public Page<PositionResponse> getPositionsByDepartment(Long departmentId, Pageable pageable) {
                return positionRepository.findByDepartmentId(departmentId, pageable).map(positionMapper::toResponse);
        }

        public PositionResponse updatePosition(Long id, PositionRequest requestDto) {
                Position existingPosition = positionRepository.findById(id)
                                .orElseThrow(() -> ResourceNotFoundException.of("Position", id));

                Department department = departmentRepository.findById(requestDto.getDepartmentId())
                                .orElseThrow(() -> ResourceNotFoundException.of("Department", requestDto.getDepartmentId()));

                JobGrade jobGrade = null;
                if (requestDto.getJobGradeId() != null) {
                        jobGrade = jobGradeRepository.findById(requestDto.getJobGradeId())
                                        .orElseThrow(() -> ResourceNotFoundException.of("JobGrade", requestDto.getJobGradeId()));
                }

                positionMapper.updateEntity(existingPosition, requestDto, department, jobGrade);

                Position updatedPosition = positionRepository.save(existingPosition);
                return positionMapper.toResponse(updatedPosition);
        }

        public void deletePosition(Long id) {
                if (!positionRepository.existsById(id)) {
                        throw ResourceNotFoundException.of("Position", id);
                }
                positionRepository.deleteById(id);
        }
}