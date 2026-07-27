package com.hrms.organization.repository;

import com.hrms.organization.entity.JobGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobGradeRepository extends JpaRepository<JobGrade, Long> {
}
