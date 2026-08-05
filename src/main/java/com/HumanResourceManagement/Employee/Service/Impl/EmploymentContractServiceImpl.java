package com.HumanResourceManagement.Employee.Service.Impl;

import com.HumanResourceManagement.Employee.DTO.EmploymentContractRequest;
import com.HumanResourceManagement.Employee.DTO.EmploymentContractResponse;
import com.HumanResourceManagement.Employee.Mapper.EmploymentContractMapper;
import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.Employee.Model.EmploymentContract;
import com.HumanResourceManagement.Employee.Repository.EmploymentContractRepository;
import com.HumanResourceManagement.Employee.Repository.EmployeeRepository;
import com.HumanResourceManagement.Employee.Service.EmploymentContractServiceInterface;
import com.HumanResourceManagement.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmploymentContractServiceImpl implements EmploymentContractServiceInterface {

    private final EmploymentContractRepository contractRepository;
    private final EmployeeRepository employeeRepository;
    private final EmploymentContractMapper contractMapper;

    @Override
    public EmploymentContractResponse createContract(EmploymentContractRequest request) {
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> ResourceNotFoundException.of("Employee", request.getEmployeeId()));

        EmploymentContract c = contractMapper.toEntity(request, employee);
        EmploymentContract saved = contractRepository.save(c);
        return contractMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmploymentContractResponse> getContractById(Long id) {
        return contractRepository.findById(id).map(contractMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EmploymentContractResponse> getContractsByEmployee(Long employeeId, Pageable pageable) {
        return contractRepository.findByEmployeeId(employeeId, pageable).map(contractMapper::toResponse);
    }

    @Override
    public EmploymentContractResponse updateContract(Long id, EmploymentContractRequest request) {
        EmploymentContract existing = contractRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("EmploymentContract", id));

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> ResourceNotFoundException.of("Employee", request.getEmployeeId()));

        contractMapper.updateEntity(existing, request, employee);
        return contractMapper.toResponse(contractRepository.save(existing));
    }

    @Override
    public void deleteContract(Long id) {
        if (!contractRepository.existsById(id)) throw ResourceNotFoundException.of("EmploymentContract", id);
        contractRepository.deleteById(id);
    }
}
