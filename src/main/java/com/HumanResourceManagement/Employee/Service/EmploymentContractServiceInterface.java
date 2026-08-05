package com.HumanResourceManagement.Employee.Service;

import com.HumanResourceManagement.Employee.DTO.EmploymentContractRequest;
import com.HumanResourceManagement.Employee.DTO.EmploymentContractResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmploymentContractServiceInterface {
    EmploymentContractResponse createContract(EmploymentContractRequest request);
    Optional<EmploymentContractResponse> getContractById(Long id);
    Page<EmploymentContractResponse> getContractsByEmployee(Long employeeId, Pageable pageable);
    EmploymentContractResponse updateContract(Long id, EmploymentContractRequest request);
    void deleteContract(Long id);
}
