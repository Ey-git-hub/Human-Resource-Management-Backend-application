package com.HumanResourceManagement.Payroll.Service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.HumanResourceManagement.Payroll.DTO.PayrollRequest;
import com.HumanResourceManagement.Payroll.DTO.PayrollResponse;
import com.HumanResourceManagement.Payroll.Model.PayrollStatus;

public interface PayrollServiceInterface {
    PayrollResponse createPayroll(PayrollRequest requestDto);

    Optional<PayrollResponse> getPayrollById(Long id);

    Page<PayrollResponse> getAllPayrolls(Pageable pageable);

    Page<PayrollResponse> getPayrollsByEmployee(Long employeeId, Pageable pageable);

    PayrollResponse updatePayroll(Long id, PayrollRequest requestDto);

    PayrollResponse updateStatus(Long id, PayrollStatus status, LocalDate paymentDate);

    void deletePayroll(Long id);
}
