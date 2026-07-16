package com.HumanResourceManagement.Leave;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HumanResourceManagement.application.model.Leave.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
}
