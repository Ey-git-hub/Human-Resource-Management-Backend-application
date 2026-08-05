package com.HumanResourceManagement.Employee.Service;

import com.HumanResourceManagement.Employee.DTO.EmployeeDocumentRequest;
import com.HumanResourceManagement.Employee.DTO.EmployeeDocumentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmployeeDocumentServiceInterface {
    EmployeeDocumentResponse createDocument(EmployeeDocumentRequest request);
    Optional<EmployeeDocumentResponse> getDocumentById(Long id);
    Page<EmployeeDocumentResponse> getDocumentsByEmployee(Long employeeId, Pageable pageable);
    void deleteDocument(Long id);
}
