package com.HumanResourceManagement.Organization;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HumanResourceManagement.application.model.Organization.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    boolean existsByName(String name);
}
