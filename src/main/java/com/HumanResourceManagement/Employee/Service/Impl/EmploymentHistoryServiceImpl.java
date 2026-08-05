package com.HumanResourceManagement.Employee.Service.Impl;

import com.HumanResourceManagement.Employee.DTO.EmploymentHistoryRequest;
import com.HumanResourceManagement.Employee.DTO.EmploymentHistoryResponse;
import com.HumanResourceManagement.Employee.Mapper.EmploymentHistoryMapper;
import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.Employee.Model.EmploymentHistory;
import com.HumanResourceManagement.Employee.Repository.EmploymentHistoryRepository;
import com.HumanResourceManagement.Employee.Repository.EmployeeRepository;
import com.HumanResourceManagement.Organization.Repository.PositionRepository;
import com.HumanResourceManagement.Organization.Repository.DepartmentRepository;
import com.HumanResourceManagement.Employee.Service.EmploymentHistoryServiceInterface;
import com.HumanResourceManagement.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmploymentHistoryServiceImpl implements EmploymentHistoryServiceInterface {

    private final EmploymentHistoryRepository historyRepository;
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;
    private final EmploymentHistoryMapper historyMapper;

    @Override
    public EmploymentHistoryResponse createHistory(EmploymentHistoryRequest request) {
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> ResourceNotFoundException.of("Employee", request.getEmployeeId()));

        var prevPos = request.getPreviousPositionId() != null
                ? positionRepository.findById(request.getPreviousPositionId()).orElse(null)
                : null;
        var newPos = positionRepository.findById(request.getNewPositionId())
                .orElseThrow(() -> ResourceNotFoundException.of("Position", request.getNewPositionId()));

        var prevDept = request.getPreviousDepartmentId() != null
                ? departmentRepository.findById(request.getPreviousDepartmentId()).orElse(null)
                : null;
        var newDept = departmentRepository.findById(request.getNewDepartmentId())
                .orElseThrow(() -> ResourceNotFoundException.of("Department", request.getNewDepartmentId()));

        var approvedBy = request.getApprovedById() != null
                ? employeeRepository.findById(request.getApprovedById()).orElse(null)
                : null;

        EmploymentHistory h = historyMapper.toEntity(request, employee, prevPos, newPos, prevDept, newDept, approvedBy);
        EmploymentHistory saved = historyRepository.save(h);
        return historyMapper.toResponse(saved);
    }

    @Override
    public Optional<EmploymentHistoryResponse> getHistoryById(Long id) {
        return historyRepository.findById(id).map(historyMapper::toResponse);
    }

    @Override
    public Page<EmploymentHistoryResponse> getHistoriesByEmployee(Long employeeId, Pageable pageable) {
        return historyRepository.findByEmployeeId(employeeId, pageable).map(historyMapper::toResponse);
    }

    @Override
    public void deleteHistory(Long id) {
        if (!historyRepository.existsById(id)) throw ResourceNotFoundException.of("EmploymentHistory", id);
        historyRepository.deleteById(id);
    }
}
