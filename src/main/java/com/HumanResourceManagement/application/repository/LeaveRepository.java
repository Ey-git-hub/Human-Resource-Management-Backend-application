package com.HumanResourceManagement.application.repository;

import com.HumanResourceManagement.application.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
}
