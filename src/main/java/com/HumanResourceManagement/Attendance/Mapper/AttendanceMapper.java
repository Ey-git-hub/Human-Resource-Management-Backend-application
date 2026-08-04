package com.HumanResourceManagement.Attendance.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Attendance.DTO.AttendanceRequest;
import com.HumanResourceManagement.Attendance.DTO.AttendanceResponse;
import com.HumanResourceManagement.Attendance.Model.Attendance;
import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.shared.util.MapperUtils;

@Component
public class AttendanceMapper {

    public AttendanceResponse toResponse(Attendance attendance) {
        AttendanceResponse response = MapperUtils.map(attendance, AttendanceResponse.class);
        if (attendance.getEmployee() != null) {
            response.setEmployee(attendance.getEmployee().getFirstName() + " " + attendance.getEmployee().getLastName());
        }
        return response;
    }

    public Attendance toEntity(AttendanceRequest request, Employee employee) {
        Attendance attendance = MapperUtils.map(request, Attendance.class);
        attendance.setEmployee(employee);
        return attendance;
    }
}
