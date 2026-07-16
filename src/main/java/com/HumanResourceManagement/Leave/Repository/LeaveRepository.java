package com.HumanResourceManagement.Leave.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HumanResourceManagement.Leave.Model.Leave;

// import com.HumanResourceManagement.application.model.Leave.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
}
