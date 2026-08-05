package com.HumanResourceManagement.Attendance.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.HumanResourceManagement.Attendance.DTO.ShiftRequest;
import com.HumanResourceManagement.Attendance.DTO.ShiftResponse;

import java.util.Optional;

public interface ShiftServiceInterface {
    Page<ShiftResponse> fetchAllShifts(Pageable pageable);

    Optional<ShiftResponse> getShift(Long id);

    ShiftResponse createShift(ShiftRequest request);

    ShiftResponse updateShift(Long id, ShiftRequest request);

    void deleteShift(Long id);
}
