package com.HumanResourceManagement.Payroll.DTO;

import com.HumanResourceManagement.Payroll.Model.PayrollStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayrollRequest {

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotNull(message = "Pay period start date is required")
    private LocalDate payPeriodStart;

    @NotNull(message = "Pay period end date is required")
    private LocalDate payPeriodEnd;

    @NotNull(message = "Basic salary is required")
    @PositiveOrZero(message = "Basic salary must be non-negative")
    private BigDecimal basicSalary;

    @PositiveOrZero(message = "Allowances must be non-negative")
    private BigDecimal allowances;

    @PositiveOrZero(message = "Deductions must be non-negative")
    private BigDecimal deductions;

    private PayrollStatus status;

    private LocalDate paymentDate;
}