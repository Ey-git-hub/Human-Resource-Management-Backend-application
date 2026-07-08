package com.HumanResourceManagement.application.repository;

import com.HumanResourceManagement.application.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long > {

    boolean existsByEmail(String email);
}
