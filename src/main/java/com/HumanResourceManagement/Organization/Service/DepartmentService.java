package com.HumanResourceManagement.Organization.Service;

import com.HumanResourceManagement.Organization.DTO.DepartmentRequest;
import com.HumanResourceManagement.Organization.DTO.DepartmentResponse;
import com.HumanResourceManagement.Organization.Mapper.DepartmentMapper;
import com.HumanResourceManagement.Organization.Model.Department;
import com.HumanResourceManagement.Organization.Repository.DepartmentRepository;
import com.HumanResourceManagement.shared.exception.DuplicateResourceException;
import com.HumanResourceManagement.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public Page<DepartmentResponse> fetchAllDepartments(Pageable pageable) {
        return departmentRepository.findAll(pageable)
                .map(departmentMapper::toResponse);
    }

    public Optional<DepartmentResponse> getDepartment(Long id) {
        return departmentRepository.findById(id)
                .map(departmentMapper::toResponse);
    }

    public DepartmentResponse createDepartment(DepartmentRequest request) {
        if (departmentRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Department already exists with name: " + request.getName());
        }
        Department department = departmentMapper.toEntity(request);
        // TODO: resolve ManagerName -> Employee manager lookup once that association is wired up
        return departmentMapper.toResponse(departmentRepository.save(department));
    }

    public DepartmentResponse updateDepartment(Long id, DepartmentRequest request) {
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Department", id));
        departmentMapper.updateEntity(existing, request);
        return departmentMapper.toResponse(departmentRepository.save(existing));
    }

    public void deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw ResourceNotFoundException.of("Department", id);
        }
        departmentRepository.deleteById(id);
    }
}
