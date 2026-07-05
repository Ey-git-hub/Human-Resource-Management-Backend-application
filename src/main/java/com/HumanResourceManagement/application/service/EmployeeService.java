package com.HumanResourceManagement.application.service;

import com.HumanResourceManagement.application.model.Employee;
import com.HumanResourceManagement.application.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public List<Employee> fetchAllEmployees() {
return employeeRepository.findAll();
    }

    public boolean createEmployee(Employee employee) {
        employeeRepository.save(employee);
        return true;

    }
}
