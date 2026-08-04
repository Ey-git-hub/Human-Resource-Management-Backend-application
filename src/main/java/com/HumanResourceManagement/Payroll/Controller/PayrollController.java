package com.HumanResourceManagement.Payroll.Controller;

import com.HumanResourceManagement.Payroll.DTO.PayrollRequest;
import com.HumanResourceManagement.Payroll.DTO.PayrollResponse;
import com.HumanResourceManagement.Payroll.Model.PayrollStatus;
import com.HumanResourceManagement.Payroll.Service.Impl.PayrollService;
import com.HumanResourceManagement.shared.util.PageableUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/payrolls")
@RequiredArgsConstructor
public class PayrollController {

    private final PayrollService payrollService;

    @PostMapping
    public ResponseEntity<PayrollResponse> createPayroll(@Valid @RequestBody PayrollRequest requestDto) {
        PayrollResponse response = payrollService.createPayroll(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PayrollResponse> getPayrollById(@PathVariable Long id) {
        return payrollService.getPayrollById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<PayrollResponse>> getAllPayrolls(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(
                payrollService.getAllPayrolls(PageableUtils.build(page, size, sortBy, direction)));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Page<PayrollResponse>> getPayrollsByEmployee(
            @PathVariable Long employeeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(
                payrollService.getPayrollsByEmployee(employeeId, PageableUtils.build(page, size, sortBy, direction)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PayrollResponse> updatePayroll(
            @PathVariable Long id,
            @Valid @RequestBody PayrollRequest requestDto) {
        return ResponseEntity.ok(payrollService.updatePayroll(id, requestDto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PayrollResponse> updatePayrollStatus(
            @PathVariable Long id,
            @RequestParam PayrollStatus status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate paymentDate) {
        return ResponseEntity.ok(payrollService.updateStatus(id, status, paymentDate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayroll(@PathVariable Long id) {
        payrollService.deletePayroll(id);
        return ResponseEntity.noContent().build();
    }
}