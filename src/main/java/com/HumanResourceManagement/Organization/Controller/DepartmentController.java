package com.HumanResourceManagement.Organization.Controller;

import com.HumanResourceManagement.Organization.DTO.DepartmentRequest;
import com.HumanResourceManagement.Organization.DTO.DepartmentResponse;
import com.HumanResourceManagement.Organization.Service.DepartmentService;
import com.HumanResourceManagement.shared.util.PageableUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departments")
public class DepartmentController {
  private final DepartmentService departmentService;

  @GetMapping
  public ResponseEntity<Page<DepartmentResponse>> getAllDepartments(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "id") String sortBy,
      @RequestParam(defaultValue = "asc") String direction) {
    return ResponseEntity.ok(departmentService.fetchAllDepartments(PageableUtils.build(page, size, sortBy, direction)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long id) {
    Optional<DepartmentResponse> department = departmentService.getDepartment(id);
    boolean found = department.isPresent();
    if (found) {
      return ResponseEntity.ok(department.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<DepartmentResponse> createDepartment(@Valid @RequestBody DepartmentRequest request) {
    DepartmentResponse created = departmentService.createDepartment(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<DepartmentResponse> updateDepartment(@PathVariable Long id,
      @Valid @RequestBody DepartmentRequest request) {
    return ResponseEntity.ok(departmentService.updateDepartment(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
    departmentService.deleteDepartment(id);
    return ResponseEntity.noContent().build();
  }
}
