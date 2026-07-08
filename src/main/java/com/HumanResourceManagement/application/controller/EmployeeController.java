package com.HumanResourceManagement.application.controller;

import com.HumanResourceManagement.application.dto.EmployeeResponse;
import com.HumanResourceManagement.application.model.Employee;
import com.HumanResourceManagement.application.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    //to get all the employees
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.fetchAllEmployees());
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id){
        Optional<EmployeeResponse> result=employeeService.getEmployeeById(id);
        boolean found=result.isPresent();
        if (found){
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }









//    @PostMapping
//    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
//        boolean addedEmployee=employeeService.createEmployee(employee);
//        if (addedEmployee){
//        return ResponseEntity.accepted().build();
//        }
//        return ResponseEntity.badRequest().build();
//    }
}