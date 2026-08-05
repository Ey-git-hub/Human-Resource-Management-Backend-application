package com.HumanResourceManagement.Attendance.Service.Impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HumanResourceManagement.Attendance.DTO.ShiftRequest;
import com.HumanResourceManagement.Attendance.DTO.ShiftResponse;
import com.HumanResourceManagement.Attendance.Mapper.ShiftMapper;
import com.HumanResourceManagement.Attendance.Model.Shift;
import com.HumanResourceManagement.Attendance.Repository.ShiftRepository;
import com.HumanResourceManagement.Attendance.Service.ShiftServiceInterface;
import com.HumanResourceManagement.shared.exception.DuplicateResourceException;
import com.HumanResourceManagement.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class ShiftServiceImpl implements ShiftServiceInterface {

    private final ShiftRepository shiftRepository;
    private final ShiftMapper shiftMapper;

    @Override
    public Page<ShiftResponse> fetchAllShifts(Pageable pageable) {
        return shiftRepository.findAll(pageable).map(shiftMapper::toResponse);
    }

    @Override
    public Optional<ShiftResponse> getShift(Long id) {
        return shiftRepository.findById(id).map(shiftMapper::toResponse);
    }

    @Override
    @Transactional
    public ShiftResponse createShift(ShiftRequest request) {
        if (shiftRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Shift already exists with name: " + request.getName());
        }
        Shift shift = shiftMapper.toEntity(request);
        Shift saved = shiftRepository.save(shift);
        return shiftMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public ShiftResponse updateShift(Long id, ShiftRequest request) {
        Shift existing = shiftRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Shift", id));
        shiftMapper.updateEntity(existing, request);
        return shiftMapper.toResponse(shiftRepository.save(existing));
    }

    @Override
    @Transactional
    public void deleteShift(Long id) {
        if (!shiftRepository.existsById(id)) {
            throw ResourceNotFoundException.of("Shift", id);
        }
        shiftRepository.deleteById(id);
    }
}
