package com.HumanResourceManagement.Organization.Service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.HumanResourceManagement.Organization.DTO.PositionRequest;
import com.HumanResourceManagement.Organization.DTO.PositionResponse;

public interface PositionServiceInterface {
    PositionResponse createPosition(PositionRequest requestDto);

    Optional<PositionResponse> getPositionById(Long id);

    Page<PositionResponse> getAllPositions(Pageable pageable);

    Page<PositionResponse> getPositionsByDepartment(Long departmentId, Pageable pageable);

    PositionResponse updatePosition(Long id, PositionRequest requestDto);

    void deletePosition(Long id);
}
