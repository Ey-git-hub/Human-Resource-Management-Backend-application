package com.HumanResourceManagement.Employee.Service;

import com.HumanResourceManagement.Employee.DTO.EmploymentHistoryRequest;
import com.HumanResourceManagement.Employee.DTO.EmploymentHistoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmploymentHistoryServiceInterface {
    EmploymentHistoryResponse createHistory(EmploymentHistoryRequest request);
    Optional<EmploymentHistoryResponse> getHistoryById(Long id);
    Page<EmploymentHistoryResponse> getHistoriesByEmployee(Long employeeId, Pageable pageable);
    void deleteHistory(Long id);
}
