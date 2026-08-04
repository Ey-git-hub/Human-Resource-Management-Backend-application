package com.HumanResourceManagement.Leave.Mapper;

import com.HumanResourceManagement.Leave.DTO.LeaveRequest;
import com.HumanResourceManagement.Leave.DTO.LeaveResponse;
import com.HumanResourceManagement.Leave.Model.Leave;
import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.shared.util.MapperUtils;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;

@Component
public class LeaveMapper {

    public LeaveResponse toResponseDto(Leave leave) {
        LeaveResponse dto = MapperUtils.map(leave, LeaveResponse.class);
        if (leave.getEmployee() != null) {
            dto.setEmployeeId(leave.getEmployee().getId());
            dto.setEmployeeName(leave.getEmployee().getFirstName() + " " + leave.getEmployee().getLastName());
        }
        dto.setNumberOfDays(ChronoUnit.DAYS.between(leave.getStartDate(), leave.getEndDate()) + 1);
        return dto;
    }

    public Leave toEntity(LeaveRequest dto, Employee employee) {
        Leave leave = MapperUtils.map(dto, Leave.class);
        leave.setEmployee(employee);
        return leave;
    }
}