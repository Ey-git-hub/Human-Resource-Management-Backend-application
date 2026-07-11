package com.HumanResourceManagement.application.repository;

import com.HumanResourceManagement.application.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

}
