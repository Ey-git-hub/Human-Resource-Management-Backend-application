package com.HumanResourceManagement.application.repository;

import com.HumanResourceManagement.application.dto.AttendanceResponse;
import com.HumanResourceManagement.application.model.Attendance;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Optional<AttendanceResponse> findByEmployeeIdAndDate(Long employeeId, LocalDate date);

}
