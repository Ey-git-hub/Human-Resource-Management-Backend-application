package com.HumanResourceManagement.Employee.Service.Impl;

import com.HumanResourceManagement.Employee.DTO.EmployeeDocumentRequest;
import com.HumanResourceManagement.Employee.DTO.EmployeeDocumentResponse;
import com.HumanResourceManagement.Employee.Mapper.EmployeeDocumentMapper;
import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.Employee.Model.EmployeeDocument;
import com.HumanResourceManagement.Employee.Repository.EmployeeDocumentRepository;
import com.HumanResourceManagement.Employee.Repository.EmployeeRepository;
import com.HumanResourceManagement.Employee.Service.EmployeeDocumentServiceInterface;
import com.HumanResourceManagement.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeDocumentServiceImpl implements EmployeeDocumentServiceInterface {

    private final EmployeeDocumentRepository documentRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeDocumentMapper documentMapper;

    @Override
    public EmployeeDocumentResponse createDocument(EmployeeDocumentRequest request) {
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> ResourceNotFoundException.of("Employee", request.getEmployeeId()));

        EmployeeDocument doc = documentMapper.toEntity(request, employee);
        doc.setUploadedAt(LocalDateTime.now());
        EmployeeDocument saved = documentRepository.save(doc);
        return documentMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeeDocumentResponse> getDocumentById(Long id) {
        return documentRepository.findById(id).map(documentMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EmployeeDocumentResponse> getDocumentsByEmployee(Long employeeId, Pageable pageable) {
        return documentRepository.findByEmployeeId(employeeId, pageable).map(documentMapper::toResponse);
    }

    @Override
    public void deleteDocument(Long id) {
        if (!documentRepository.existsById(id)) throw ResourceNotFoundException.of("EmployeeDocument", id);
        documentRepository.deleteById(id);
    }
}
