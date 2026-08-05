package com.HumanResourceManagement.Employee.Repository;

import com.HumanResourceManagement.Employee.Model.EmploymentHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmploymentHistoryRepository extends JpaRepository<EmploymentHistory, Long> {
    List<EmploymentHistory> findByEmployeeId(Long employeeId);
    Page<EmploymentHistory> findByEmployeeId(Long employeeId, Pageable pageable);
}
