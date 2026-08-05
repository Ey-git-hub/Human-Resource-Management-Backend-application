package com.HumanResourceManagement.Attendance.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HumanResourceManagement.Attendance.DTO.WorkScheduleRequest;
import com.HumanResourceManagement.Attendance.DTO.WorkScheduleResponse;
import com.HumanResourceManagement.Attendance.Mapper.WorkScheduleMapper;
import com.HumanResourceManagement.Attendance.Model.WorkSchedule;
import com.HumanResourceManagement.Attendance.Repository.WorkScheduleRepository;
import com.HumanResourceManagement.Attendance.Service.WorkScheduleServiceInterface;
import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.Employee.Repository.EmployeeRepository;
import com.HumanResourceManagement.Attendance.Model.Shift;
import com.HumanResourceManagement.Attendance.Repository.ShiftRepository;
import com.HumanResourceManagement.shared.exception.ResourceNotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkScheduleServiceImpl implements WorkScheduleServiceInterface {

    private final WorkScheduleRepository workScheduleRepository;
    private final EmployeeRepository employeeRepository;
    private final ShiftRepository shiftRepository;
    private final WorkScheduleMapper mapper;

    @Override
    public Page<WorkScheduleResponse> fetchAll(Pageable pageable) {
        return workScheduleRepository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public Optional<WorkScheduleResponse> getById(Long id) {
        return workScheduleRepository.findById(id).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public WorkScheduleResponse create(WorkScheduleRequest request) {
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> ResourceNotFoundException.of("Employee", request.getEmployeeId()));

        Shift shift = shiftRepository.findById(request.getShiftId())
                .orElseThrow(() -> ResourceNotFoundException.of("Shift", request.getShiftId()));

        WorkSchedule entity = mapper.toEntity(request, employee, shift);
        WorkSchedule saved = workScheduleRepository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public WorkScheduleResponse update(Long id, WorkScheduleRequest request) {
        WorkSchedule existing = workScheduleRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("WorkSchedule", id));

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> ResourceNotFoundException.of("Employee", request.getEmployeeId()));

        Shift shift = shiftRepository.findById(request.getShiftId())
                .orElseThrow(() -> ResourceNotFoundException.of("Shift", request.getShiftId()));

        mapper.updateEntity(existing, request, employee, shift);
        return mapper.toResponse(workScheduleRepository.save(existing));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!workScheduleRepository.existsById(id)) {
            throw ResourceNotFoundException.of("WorkSchedule", id);
        }
        workScheduleRepository.deleteById(id);
    }
}
