package com.HumanResourceManagement.application.repository;

import com.HumanResourceManagement.application.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    boolean existsByName(String name);
}
