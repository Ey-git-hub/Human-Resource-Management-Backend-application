package com.HumanResourceManagement.Payroll.Repository;

import com.HumanResourceManagement.Payroll.Model.Payroll;
import com.HumanResourceManagement.Payroll.Model.PayrollStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    Page<Payroll> findByEmployeeId(Long employeeId, Pageable pageable);

    Page<Payroll> findByStatus(PayrollStatus status, Pageable pageable);

    Page<Payroll> findByPayPeriodStartBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}