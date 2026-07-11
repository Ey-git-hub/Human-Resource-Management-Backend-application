package com.HumanResourceManagement.application.repository;

import com.HumanResourceManagement.application.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByEmail(String email);
}
