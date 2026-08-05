package com.HumanResourceManagement.Attendance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HumanResourceManagement.Attendance.Model.WorkSchedule;

public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Long> {

}
