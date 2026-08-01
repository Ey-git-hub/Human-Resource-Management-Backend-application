package com.HumanResourceManagement.Employee.Service;

import com.HumanResourceManagement.Employee.DTO.EmployeeRequest;
import com.HumanResourceManagement.Employee.DTO.EmployeeResponse;
import com.HumanResourceManagement.Employee.Mapper.EmployeeMapper;
import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.Employee.Repository.EmployeeRepository;
import com.HumanResourceManagement.Organization.Model.Department;
import com.HumanResourceManagement.Organization.Repository.DepartmentRepository;
import com.HumanResourceManagement.shared.exception.DuplicateResourceException;
import com.HumanResourceManagement.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;

    public Page<EmployeeResponse> fetchAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable)
                .map(employeeMapper::toResponse);
    }

    public Optional<EmployeeResponse> getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toResponse);
    }

    public EmployeeResponse createEmployee(EmployeeRequest request) {
        // Check if employee already exists with email
        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Employee already exists with this email: " + request.getEmail());
        }

        Department department = resolveDepartment(request.getDepartmentId());
        Employee employee = employeeMapper.toEntity(request, department);

        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toResponse(savedEmployee);
    }

    public EmployeeResponse UpdateEmployee(Long id, EmployeeRequest request) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Employee", id));

        if (!existing.getEmail().equals(request.getEmail())
                && employeeRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Employee already exists with this email: " + request.getEmail());
        }

        Department department = request.getDepartmentId() != null
                ? resolveDepartment(request.getDepartmentId())
                : null;

        employeeMapper.updateEntity(existing, request, department);
        return employeeMapper.toResponse(employeeRepository.save(existing));
    }

    public void deleteEmployee(Long id) {
        Employee result = employeeRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Employee", id));
        employeeRepository.delete(result);
    }

    private Department resolveDepartment(Long departmentId) {
        if (departmentId == null) {
            return null;
        }
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> ResourceNotFoundException.of("Department", departmentId));
    }

}
