package com.HumanResourceManagement.Payroll.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Payroll.DTO.PayrollRequest;
import com.HumanResourceManagement.Payroll.DTO.PayrollResponse;
import com.HumanResourceManagement.Payroll.Model.Payroll;
import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.Payroll.Model.PayrollStatus;

// import java.math.BigDecimal;

@Component
public class PayrollMapper {

    public PayrollResponse toResponse(Payroll payroll) {
        return PayrollResponse.fromEntity(payroll);
    }

    public Payroll toEntity(PayrollRequest request, Employee employee) {
        Payroll payroll = new Payroll();
        payroll.setEmployee(employee);
        payroll.setPayPeriodStart(request.getPayPeriodStart());
        payroll.setPayPeriodEnd(request.getPayPeriodEnd());
        payroll.setBasicSalary(request.getBasicSalary() != null ? request.getBasicSalary().doubleValue() : 0.0);
        payroll.setAllowances(request.getAllowances() != null ? request.getAllowances().doubleValue() : 0.0);
        payroll.setDeductions(request.getDeductions() != null ? request.getDeductions().doubleValue() : 0.0);
        double net = payroll.getBasicSalary() + payroll.getAllowances() - payroll.getDeductions();
        payroll.setNetSalary(net);
        payroll.setStatus(request.getStatus() != null ? request.getStatus() : PayrollStatus.PENDING);
        payroll.setPaymentDate(request.getPaymentDate());
        return payroll;
    }

    public void updateEntity(Payroll existing, PayrollRequest request, Employee employee) {
        if (employee != null) {
            existing.setEmployee(employee);
        }
        existing.setPayPeriodStart(request.getPayPeriodStart());
        existing.setPayPeriodEnd(request.getPayPeriodEnd());

        double basic = request.getBasicSalary() != null ? request.getBasicSalary().doubleValue() : 0.0;
        double allowances = request.getAllowances() != null ? request.getAllowances().doubleValue() : 0.0;
        double deductions = request.getDeductions() != null ? request.getDeductions().doubleValue() : 0.0;

        existing.setBasicSalary(basic);
        existing.setAllowances(allowances);
        existing.setDeductions(deductions);
        existing.setNetSalary(basic + allowances - deductions);

        if (request.getStatus() != null) {
            existing.setStatus(request.getStatus());
        }
        existing.setPaymentDate(request.getPaymentDate());
    }
}
