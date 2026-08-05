package com.HumanResourceManagement.Attendance.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.HumanResourceManagement.Attendance.DTO.OvertimeRequestRequest;
import com.HumanResourceManagement.Attendance.DTO.OvertimeRequestResponse;

import java.util.Optional;

public interface OvertimeRequestServiceInterface {
    Page<OvertimeRequestResponse> fetchAll(Pageable pageable);

    Optional<OvertimeRequestResponse> getById(Long id);

    OvertimeRequestResponse create(OvertimeRequestRequest request);

    OvertimeRequestResponse update(Long id, OvertimeRequestRequest request);

    void delete(Long id);
}
