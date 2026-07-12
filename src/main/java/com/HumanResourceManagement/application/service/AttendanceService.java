package com.HumanResourceManagement.application.service;

import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import com.HumanResourceManagement.application.dto.AttendanceRequest;
import com.HumanResourceManagement.application.dto.AttendanceResponse;

import lombok.RequiredArgsConstructor;

// import org.jspecify.annotations.Nullable;
@Service
@RequiredArgsConstructor
public class AttendanceService {

    public List<AttendanceResponse> getAllAttendance() {

    }

    public Optional<AttendanceResponse> getAttendanceById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAttendanceById'");
    }

    public AttendanceResponse addNewAttendance(AttendanceRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addNewAttendance'");
    }

    public @Nullable Object updateAttendance(Long id, AttendanceRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAttendance'");
    }

}
