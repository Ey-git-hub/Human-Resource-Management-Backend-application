package com.HumanResourceManagement.Attendance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HumanResourceManagement.Attendance.Model.OvertimeRequest;

public interface OvertimeRequestRepository extends JpaRepository<OvertimeRequest, Long> {
}
