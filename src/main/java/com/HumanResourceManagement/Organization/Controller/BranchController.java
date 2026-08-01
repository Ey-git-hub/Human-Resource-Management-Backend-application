package com.HumanResourceManagement.Organization.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.HumanResourceManagement.Organization.DTO.BranchRequest;
import com.HumanResourceManagement.Organization.DTO.BranchResponse;
import com.HumanResourceManagement.Organization.Service.BranchService;
import com.HumanResourceManagement.shared.util.PageableUtils;

@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<BranchResponse> create(@Valid @RequestBody BranchRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(branchService.createBranch(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchResponse> getById(@PathVariable Long id) {
        return branchService.getBranchById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<BranchResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(branchService.getAllBranches(PageableUtils.build(page, size, sortBy, direction)));
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<Page<BranchResponse>> getByOrganization(
            @PathVariable Long organizationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(branchService.getBranchesByOrganization(organizationId,
                PageableUtils.build(page, size, sortBy, direction)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchResponse> update(@PathVariable Long id, @Valid @RequestBody BranchRequest dto) {
        return ResponseEntity.ok(branchService.updateBranch(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
}
