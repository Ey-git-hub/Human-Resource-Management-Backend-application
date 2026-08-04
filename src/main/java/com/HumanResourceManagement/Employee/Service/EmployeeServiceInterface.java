package com.HumanResourceManagement.Employee.Service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.HumanResourceManagement.Employee.DTO.EmployeeRequest;
import com.HumanResourceManagement.Employee.DTO.EmployeeResponse;

public interface EmployeeServiceInterface {
    Page<EmployeeResponse> fetchAllEmployees(Pageable pageable);

    Optional<EmployeeResponse> getEmployeeById(Long id);

    EmployeeResponse createEmployee(EmployeeRequest request);

    EmployeeResponse UpdateEmployee(Long id, EmployeeRequest request);

    void deleteEmployee(Long id);
}
