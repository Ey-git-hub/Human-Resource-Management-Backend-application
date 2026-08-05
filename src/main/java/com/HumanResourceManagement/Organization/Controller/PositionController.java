package com.HumanResourceManagement.Organization.Controller;

import com.HumanResourceManagement.Organization.DTO.PositionRequest;
import com.HumanResourceManagement.Organization.DTO.PositionResponse;
// import com.HumanResourceManagement.Organization.Service.Impl.PositionService;
import com.HumanResourceManagement.Organization.Service.Impl.PositionServiceImpl;
import com.HumanResourceManagement.shared.util.PageableUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionServiceImpl positionService;

    @PostMapping
    public ResponseEntity<PositionResponse> createPosition(@Valid @RequestBody PositionRequest requestDto) {
        PositionResponse createdPosition = positionService.createPosition(requestDto);
        return new ResponseEntity<>(createdPosition, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionResponse> getPositionById(@PathVariable Long id) {
        return positionService.getPositionById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<PositionResponse>> getAllPositions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(positionService.getAllPositions(PageableUtils.build(page, size, sortBy, direction)));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<Page<PositionResponse>> getPositionsByDepartment(
            @PathVariable Long departmentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(positionService.getPositionsByDepartment(departmentId,
                PageableUtils.build(page, size, sortBy, direction)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PositionResponse> updatePosition(
            @PathVariable Long id,
            @Valid @RequestBody PositionRequest requestDto) {
        return ResponseEntity.ok(positionService.updatePosition(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable Long id) {
        positionService.deletePosition(id);
        return ResponseEntity.noContent().build();
    }
}