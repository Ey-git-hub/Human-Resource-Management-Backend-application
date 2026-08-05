package com.HumanResourceManagement.Employee.Repository;

import com.HumanResourceManagement.Employee.Model.EmploymentContract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmploymentContractRepository extends JpaRepository<EmploymentContract, Long> {
    List<EmploymentContract> findByEmployeeId(Long employeeId);
    Page<EmploymentContract> findByEmployeeId(Long employeeId, Pageable pageable);
}
