package com.HumanResourceManagement.application.controller;

import com.HumanResourceManagement.application.dto.DepartmentResponse;
import com.HumanResourceManagement.application.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
  @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments(){
      return ResponseEntity.ok(departmentService.fetchAllDepartments());
  }
  @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long id){

  }
}
