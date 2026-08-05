package com.HumanResourceManagement.Organization.Controller;

import com.HumanResourceManagement.Organization.DTO.JobGradeRequest;
import com.HumanResourceManagement.Organization.DTO.JobGradeResponse;
// import com.HumanResourceManagement.Organization.Service.Impl.JobGradeService;
import com.HumanResourceManagement.Organization.Service.Impl.JobGradeServiceImpl;
import com.HumanResourceManagement.shared.util.PageableUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-grades")
@RequiredArgsConstructor
public class JobGradeController {

    private final JobGradeServiceImpl jobGradeService;

    @PostMapping
    public ResponseEntity<JobGradeResponse> createJobGrade(@Valid @RequestBody JobGradeRequest requestDto) {
        JobGradeResponse createdJobGrade = jobGradeService.createJobGrade(requestDto);
        return new ResponseEntity<>(createdJobGrade, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobGradeResponse> getJobGradeById(@PathVariable Long id) {
        return jobGradeService.getJobGradeById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<JobGradeResponse>> getAllJobGrades(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(jobGradeService.getAllJobGrades(PageableUtils.build(page, size, sortBy, direction)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobGradeResponse> updateJobGrade(
            @PathVariable Long id,
            @Valid @RequestBody JobGradeRequest requestDto) {
        return ResponseEntity.ok(jobGradeService.updateJobGrade(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobGrade(@PathVariable Long id) {
        jobGradeService.deleteJobGrade(id);
        return ResponseEntity.noContent().build();
    }
}