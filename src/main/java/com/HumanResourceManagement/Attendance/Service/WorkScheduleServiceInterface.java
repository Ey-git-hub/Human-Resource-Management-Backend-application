package com.HumanResourceManagement.Attendance.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.HumanResourceManagement.Attendance.DTO.WorkScheduleRequest;
import com.HumanResourceManagement.Attendance.DTO.WorkScheduleResponse;

import java.util.Optional;

public interface WorkScheduleServiceInterface {
    Page<WorkScheduleResponse> fetchAll(Pageable pageable);

    Optional<WorkScheduleResponse> getById(Long id);

    WorkScheduleResponse create(WorkScheduleRequest request);

    WorkScheduleResponse update(Long id, WorkScheduleRequest request);

    void delete(Long id);
}
