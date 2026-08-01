package com.HumanResourceManagement.Attendance.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Attendance.DTO.AttendanceRequest;
import com.HumanResourceManagement.Attendance.DTO.AttendanceResponse;
import com.HumanResourceManagement.Attendance.Model.Attendance;
import com.HumanResourceManagement.Employee.Model.Employee;

@Component
public class AttendanceMapper {

    public AttendanceResponse toResponse(Attendance attendance) {
        AttendanceResponse response = new AttendanceResponse();
        response.setId(attendance.getId());
        response.setCheckInTime(attendance.getCheckInTime());
        response.setCheckOutTime(attendance.getCheckOutTime());
        response.setStatus(attendance.getStatus());
        response.setDate(attendance.getDate());
        if (attendance.getEmployee() != null) {
            response.setEmployee(
                    attendance.getEmployee().getFirstName() + " " +
                            attendance.getEmployee().getLastName());
        }
        response.setCreatedAt(attendance.getCreatedAt());
        return response;
    }

    public Attendance toEntity(AttendanceRequest request, Employee employee) {
        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);
        attendance.setDate(request.getDate());
        attendance.setCheckInTime(request.getCheckInTime());
        attendance.setCheckOutTime(request.getCheckOutTime());
        attendance.setStatus(request.getStatus());
        return attendance;
    }
}
