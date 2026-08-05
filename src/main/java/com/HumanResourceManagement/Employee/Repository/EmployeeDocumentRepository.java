package com.HumanResourceManagement.Employee.Repository;

import com.HumanResourceManagement.Employee.Model.EmployeeDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeDocumentRepository extends JpaRepository<EmployeeDocument, Long> {
    List<EmployeeDocument> findByEmployeeId(Long employeeId);
    Page<EmployeeDocument> findByEmployeeId(Long employeeId, Pageable pageable);
}
