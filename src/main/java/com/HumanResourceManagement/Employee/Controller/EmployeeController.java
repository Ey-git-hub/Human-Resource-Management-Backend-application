package com.HumanResourceManagement.Employee.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.HumanResourceManagement.Employee.DTO.EmployeeRequest;
import com.HumanResourceManagement.Employee.DTO.EmployeeResponse;
import com.HumanResourceManagement.Employee.Service.Impl.EmployeeServiceImpl;
import com.HumanResourceManagement.Employee.DTO.EmployeeDocumentRequest;
import com.HumanResourceManagement.Employee.DTO.EmployeeDocumentResponse;
import com.HumanResourceManagement.Employee.DTO.EmploymentContractRequest;
import com.HumanResourceManagement.Employee.DTO.EmploymentContractResponse;
import com.HumanResourceManagement.Employee.DTO.EmploymentHistoryRequest;
import com.HumanResourceManagement.Employee.DTO.EmploymentHistoryResponse;
import com.HumanResourceManagement.Employee.Service.Impl.EmployeeDocumentServiceImpl;
import com.HumanResourceManagement.Employee.Service.Impl.EmploymentContractServiceImpl;
import com.HumanResourceManagement.Employee.Service.Impl.EmploymentHistoryServiceImpl;
import com.HumanResourceManagement.shared.util.PageableUtils;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;
    private final EmployeeDocumentServiceImpl documentService;
    private final EmploymentContractServiceImpl contractService;
    private final EmploymentHistoryServiceImpl historyService;

    // to get all the employees (paginated)
    @GetMapping
    public ResponseEntity<Page<EmployeeResponse>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(employeeService.fetchAllEmployees(PageableUtils.build(page, size, sortBy, direction)));
    }

    // Employee Documents
    @PostMapping("/{id}/documents")
    public ResponseEntity<EmployeeDocumentResponse> addDocument(@PathVariable Long id,
            @Valid @RequestBody EmployeeDocumentRequest request) {
        // ensure path id matches request.employeeId
        if (request.getEmployeeId() == null) request.setEmployeeId(id);
        EmployeeDocumentResponse created = documentService.createDocument(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}/documents")
    public ResponseEntity<Page<EmployeeDocumentResponse>> getDocumentsByEmployee(@PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var result = documentService.getDocumentsByEmployee(id, PageableUtils.build(page, size, "id", "asc"));
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/documents/{docId}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long docId) {
        documentService.deleteDocument(docId);
        return ResponseEntity.noContent().build();
    }

    // Employment Contracts
    @PostMapping("/{id}/contracts")
    public ResponseEntity<EmploymentContractResponse> addContract(@PathVariable Long id,
            @Valid @RequestBody EmploymentContractRequest request) {
        if (request.getEmployeeId() == null) request.setEmployeeId(id);
        EmploymentContractResponse created = contractService.createContract(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}/contracts")
    public ResponseEntity<Page<EmploymentContractResponse>> getContractsByEmployee(@PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var result = contractService.getContractsByEmployee(id, PageableUtils.build(page, size, "id", "asc"));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/contracts/{contractId}")
    public ResponseEntity<EmploymentContractResponse> updateContract(@PathVariable Long contractId,
            @Valid @RequestBody EmploymentContractRequest request) {
        EmploymentContractResponse updated = contractService.updateContract(contractId, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/contracts/{contractId}")
    public ResponseEntity<Void> deleteContract(@PathVariable Long contractId) {
        contractService.deleteContract(contractId);
        return ResponseEntity.noContent().build();
    }

    // Employment History
    @PostMapping("/{id}/histories")
    public ResponseEntity<EmploymentHistoryResponse> addHistory(@PathVariable Long id,
            @Valid @RequestBody EmploymentHistoryRequest request) {
        if (request.getEmployeeId() == null) request.setEmployeeId(id);
        EmploymentHistoryResponse created = historyService.createHistory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}/histories")
    public ResponseEntity<Page<EmploymentHistoryResponse>> getHistoriesByEmployee(@PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var result = historyService.getHistoriesByEmployee(id, PageableUtils.build(page, size, "id", "asc"));
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/histories/{historyId}")
    public ResponseEntity<Void> deleteHistory(@PathVariable Long historyId) {
        historyService.deleteHistory(historyId);
        return ResponseEntity.noContent().build();
    }

    // to get a single employee using id
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        Optional<EmployeeResponse> result = employeeService.getEmployeeById(id);
        boolean found = result.isPresent();
        if (found) {

            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    // to create new employee
    @PostMapping
    public ResponseEntity<EmployeeResponse> addEmployee(@Valid @RequestBody EmployeeRequest request) {
        EmployeeResponse created = employeeService.createEmployee(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);

    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.UpdateEmployee(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();

    }
}