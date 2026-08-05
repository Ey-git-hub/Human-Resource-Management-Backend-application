package com.HumanResourceManagement.Attendance.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Attendance.DTO.OvertimeRequestRequest;
import com.HumanResourceManagement.Attendance.DTO.OvertimeRequestResponse;
import com.HumanResourceManagement.Attendance.Model.OvertimeRequest;
import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.shared.util.MapperUtils;

@Component
public class OvertimeRequestMapper {

    public OvertimeRequestResponse toResponse(OvertimeRequest entity) {
        OvertimeRequestResponse response = MapperUtils.map(entity, OvertimeRequestResponse.class);
        if (entity.getEmployee() != null) {
            response.setEmployeeId(entity.getEmployee().getId());
            response.setEmployeeName(entity.getEmployee().getFirstName() + " " + entity.getEmployee().getLastName());
        }
        if (entity.getApprovedBy() != null) {
            response.setApprovedById(entity.getApprovedBy().getId());
            response.setApprovedByName(entity.getApprovedBy().getFirstName() + " " + entity.getApprovedBy().getLastName());
        }
        return response;
    }

    public OvertimeRequest toEntity(OvertimeRequestRequest request, Employee employee, Employee approvedBy) {
        OvertimeRequest entity = MapperUtils.map(request, OvertimeRequest.class);
        entity.setEmployee(employee);
        entity.setApprovedBy(approvedBy);
        return entity;
    }

    public void updateEntity(OvertimeRequest existing, OvertimeRequestRequest request, Employee employee, Employee approvedBy) {
        MapperUtils.copy(request, existing);
        if (employee != null) existing.setEmployee(employee);
        if (approvedBy != null) existing.setApprovedBy(approvedBy);
    }
}
