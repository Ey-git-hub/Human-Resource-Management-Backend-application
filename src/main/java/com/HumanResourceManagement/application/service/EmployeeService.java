package com.HumanResourceManagement.application.service;

import com.HumanResourceManagement.application.dto.EmployeeRequest;
import com.HumanResourceManagement.application.dto.EmployeeResponse;
import com.HumanResourceManagement.application.model.Employee;
import com.HumanResourceManagement.application.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<EmployeeResponse> fetchAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(EmployeeResponse::fromEmployee)
                .collect(Collectors.toList());
    }

    public Optional<EmployeeResponse> getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(EmployeeResponse::fromEmployee);
    }

    public EmployeeResponse createEmployee(EmployeeRequest request) {
        employeeRepository.save(request);

    }


//    public boolean createEmployee(Employee employee) {
//        employeeRepository.save(employee);
//        return true;
//
//    }
}
