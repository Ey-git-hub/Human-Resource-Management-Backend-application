package com.HumanResourceManagement.Attendance.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Attendance.DTO.ShiftRequest;
import com.HumanResourceManagement.Attendance.DTO.ShiftResponse;
import com.HumanResourceManagement.Attendance.Model.Shift;
import com.HumanResourceManagement.shared.util.MapperUtils;

@Component
public class ShiftMapper {

    public ShiftResponse toResponse(Shift shift) {
        return MapperUtils.map(shift, ShiftResponse.class);
    }

    public Shift toEntity(ShiftRequest request) {
        Shift shift = MapperUtils.map(request, Shift.class);
        return shift;
    }

    public void updateEntity(Shift existing, ShiftRequest request) {
        MapperUtils.copy(request, existing);
        // Enums and primitive conversions handled by BeanUtils where applicable
    }
}
