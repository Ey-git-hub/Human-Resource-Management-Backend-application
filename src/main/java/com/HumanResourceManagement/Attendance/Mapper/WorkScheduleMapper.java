package com.HumanResourceManagement.Attendance.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Attendance.DTO.WorkScheduleRequest;
import com.HumanResourceManagement.Attendance.DTO.WorkScheduleResponse;
import com.HumanResourceManagement.Attendance.Model.WorkSchedule;
import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.Attendance.Model.Shift;
import com.HumanResourceManagement.shared.util.MapperUtils;

@Component
public class WorkScheduleMapper {

    public WorkScheduleResponse toResponse(WorkSchedule entity) {
        WorkScheduleResponse response = MapperUtils.map(entity, WorkScheduleResponse.class);
        if (entity.getEmployee() != null) {
            response.setEmployeeId(entity.getEmployee().getId());
            response.setEmployeeName(entity.getEmployee().getFirstName() + " " + entity.getEmployee().getLastName());
        }
        if (entity.getShift() != null) {
            response.setShiftId(entity.getShift().getId());
            response.setShiftName(entity.getShift().getName());
        }
        return response;
    }

    public WorkSchedule toEntity(WorkScheduleRequest request, Employee employee, Shift shift) {
        WorkSchedule entity = MapperUtils.map(request, WorkSchedule.class);
        entity.setEmployee(employee);
        entity.setShift(shift);
        return entity;
    }

    public void updateEntity(WorkSchedule existing, WorkScheduleRequest request, Employee employee, Shift shift) {
        MapperUtils.copy(request, existing);
        if (employee != null) existing.setEmployee(employee);
        if (shift != null) existing.setShift(shift);
    }
}
