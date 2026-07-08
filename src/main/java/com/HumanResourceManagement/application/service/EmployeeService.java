package com.HumanResourceManagement.application.service;

import com.HumanResourceManagement.application.dto.EmployeeRequest;
import com.HumanResourceManagement.application.dto.EmployeeResponse;
import com.HumanResourceManagement.application.model.Department;
import com.HumanResourceManagement.application.model.Employee;
import com.HumanResourceManagement.application.repository.DepartmentRepository;
import com.HumanResourceManagement.application.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

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
        if(employeeRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("employee already exists with this email: "+request.getEmail());

        }
        Employee employee=new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setJobTitle(request.getJobTitle());
        employee.setSalary(request.getSalary());
        employee.setHireDate(request.getHireDate());
        employee.setStatus(request.getStatus());
        if (request.getDepartmentId()!=null){
            Department department=departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(()-> new IllegalArgumentException("department not found with id: "+ request.getDepartmentId()));
            employee.setDepartment(department);
        }
        return EmployeeResponse.fromEmployee(employeeRepository.save(employee));

    }

    public EmployeeResponse UpdateEmployee(Long id, EmployeeRequest request) {
       Employee existing= employeeRepository.findById(id)
               .orElseThrow(()-> new IllegalArgumentException("Employee not Found with id: "+id));
        existing.setFirstName(request.getFirstName());
        existing.setLastName(request.getLastName());
//        existing.setEmail(request.getEmail());
        existing.setPhoneNumber(request.getPhoneNumber());
        existing.setJobTitle(request.getJobTitle());
        existing.setSalary(request.getSalary());
        existing.setHireDate(request.getHireDate());
        existing.setStatus(request.getStatus());
        if(!existing.getEmail().equals(request.getEmail())){
            if(employeeRepository.existsByEmail(request.getEmail())){
                throw new IllegalArgumentException("employee already exists with this email :"+request.getEmail());
            }
            existing.setEmail(request.getEmail());
        }
        if (request.getDepartmentId()!=null){
            Department department =departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(()->new IllegalArgumentException("department is not found with id: "+request.getDepartmentId()));
            existing.setDepartment(department);
        }


return EmployeeResponse.fromEmployee(employeeRepository.save(existing));


    }

    public void deleteEmployee(Long id) {
        Employee result=employeeRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("there is no employee with id: "+id));
         employeeRepository.delete(result);

    }
}
