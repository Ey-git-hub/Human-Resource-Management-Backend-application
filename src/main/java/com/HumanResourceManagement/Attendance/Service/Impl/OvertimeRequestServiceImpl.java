package com.HumanResourceManagement.Attendance.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HumanResourceManagement.Attendance.DTO.OvertimeRequestRequest;
import com.HumanResourceManagement.Attendance.DTO.OvertimeRequestResponse;
import com.HumanResourceManagement.Attendance.Mapper.OvertimeRequestMapper;
import com.HumanResourceManagement.Attendance.Model.OvertimeRequest;
import com.HumanResourceManagement.Attendance.Repository.OvertimeRequestRepository;
import com.HumanResourceManagement.Attendance.Service.OvertimeRequestServiceInterface;
import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.Employee.Repository.EmployeeRepository;
import com.HumanResourceManagement.shared.exception.ResourceNotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OvertimeRequestServiceImpl implements OvertimeRequestServiceInterface {

    private final OvertimeRequestRepository overtimeRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final OvertimeRequestMapper mapper;

    @Override
    public Page<OvertimeRequestResponse> fetchAll(Pageable pageable) {
        return overtimeRequestRepository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public Optional<OvertimeRequestResponse> getById(Long id) {
        return overtimeRequestRepository.findById(id).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public OvertimeRequestResponse create(OvertimeRequestRequest request) {
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> ResourceNotFoundException.of("Employee", request.getEmployeeId()));

        Employee approvedBy = null;
        if (request.getApprovedById() != null) {
            approvedBy = employeeRepository.findById(request.getApprovedById())
                    .orElseThrow(() -> ResourceNotFoundException.of("Employee", request.getApprovedById()));
        }

        OvertimeRequest entity = mapper.toEntity(request, employee, approvedBy);
        OvertimeRequest saved = overtimeRequestRepository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public OvertimeRequestResponse update(Long id, OvertimeRequestRequest request) {
        OvertimeRequest existing = overtimeRequestRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("OvertimeRequest", id));

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> ResourceNotFoundException.of("Employee", request.getEmployeeId()));

        Employee approvedBy = null;
        if (request.getApprovedById() != null) {
            approvedBy = employeeRepository.findById(request.getApprovedById())
                    .orElseThrow(() -> ResourceNotFoundException.of("Employee", request.getApprovedById()));
        }

        mapper.updateEntity(existing, request, employee, approvedBy);
        return mapper.toResponse(overtimeRequestRepository.save(existing));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!overtimeRequestRepository.existsById(id)) {
            throw ResourceNotFoundException.of("OvertimeRequest", id);
        }
        overtimeRequestRepository.deleteById(id);
    }
}
