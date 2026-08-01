package com.HumanResourceManagement.Payroll.Service;

import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.Employee.Repository.EmployeeRepository;
import com.HumanResourceManagement.Payroll.DTO.PayrollRequest;
import com.HumanResourceManagement.Payroll.DTO.PayrollResponse;
import com.HumanResourceManagement.Payroll.Mapper.PayrollMapper;
import com.HumanResourceManagement.Payroll.Model.Payroll;
import com.HumanResourceManagement.Payroll.Model.PayrollStatus;
import com.HumanResourceManagement.Payroll.Repository.PayrollRepository;
import com.HumanResourceManagement.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PayrollService {

    private final PayrollRepository payrollRepository;
    private final EmployeeRepository employeeRepository;
    private final PayrollMapper payrollMapper;

    public PayrollResponse createPayroll(PayrollRequest requestDto) {
        Employee employee = employeeRepository.findById(requestDto.getEmployeeId())
                .orElseThrow(() -> ResourceNotFoundException.of("Employee", requestDto.getEmployeeId()));

        Payroll payroll = payrollMapper.toEntity(requestDto, employee);
        Payroll savedPayroll = payrollRepository.save(payroll);

        return payrollMapper.toResponse(savedPayroll);
    }
    @Transactional(readOnly = true)
    public Optional<PayrollResponse> getPayrollById(Long id) {
        return payrollRepository.findById(id).map(payrollMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<PayrollResponse> getAllPayrolls(Pageable pageable) {
        return payrollRepository.findAll(pageable).map(payrollMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<PayrollResponse> getPayrollsByEmployee(Long employeeId, Pageable pageable) {
        return payrollRepository.findByEmployeeId(employeeId, pageable).map(payrollMapper::toResponse);
    }

    public PayrollResponse updatePayroll(Long id, PayrollRequest requestDto) {
        Payroll existingPayroll = payrollRepository.findById(id)
            .orElseThrow(() -> ResourceNotFoundException.of("Payroll", id));

        Employee employee = employeeRepository.findById(requestDto.getEmployeeId())
            .orElseThrow(() -> ResourceNotFoundException.of("Employee", requestDto.getEmployeeId()));

        payrollMapper.updateEntity(existingPayroll, requestDto, employee);
        Payroll updatedPayroll = payrollRepository.save(existingPayroll);
        return payrollMapper.toResponse(updatedPayroll);
    }

    public PayrollResponse updateStatus(Long id, PayrollStatus status, LocalDate paymentDate) {
        Payroll payroll = payrollRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Payroll", id));

        payroll.setStatus(status);
        if (status == PayrollStatus.PAID && paymentDate == null) {
            payroll.setPaymentDate(LocalDate.now());
        } else if (paymentDate != null) {
            payroll.setPaymentDate(paymentDate);
        }

        Payroll updated = payrollRepository.save(payroll);
        return payrollMapper.toResponse(updated);
    }

    public void deletePayroll(Long id) {
        if (!payrollRepository.existsById(id)) {
            throw ResourceNotFoundException.of("Payroll", id);
        }
        payrollRepository.deleteById(id);
    }
}