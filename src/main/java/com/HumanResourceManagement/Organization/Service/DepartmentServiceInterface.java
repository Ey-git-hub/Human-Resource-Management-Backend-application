package com.HumanResourceManagement.Organization.Service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.HumanResourceManagement.Organization.DTO.DepartmentRequest;
import com.HumanResourceManagement.Organization.DTO.DepartmentResponse;

public interface DepartmentServiceInterface {
    Page<DepartmentResponse> fetchAllDepartments(Pageable pageable);

    Optional<DepartmentResponse> getDepartment(Long id);

    DepartmentResponse createDepartment(DepartmentRequest request);

    DepartmentResponse updateDepartment(Long id, DepartmentRequest request);

    void deleteDepartment(Long id);
}
