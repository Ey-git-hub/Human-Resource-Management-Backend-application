package com.HumanResourceManagement.Attendance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HumanResourceManagement.Attendance.Model.Shift;

public interface ShiftRepository extends JpaRepository<Shift, Long> {

    boolean existsByName(String name);
}
