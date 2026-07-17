package com.HumanResourceManagement.Leave.Services;

import org.springframework.stereotype.Service;

import com.HumanResourceManagement.Employee.Repository.EmployeeRepository;
import com.HumanResourceManagement.Leave.Mapper.LeaveMapper;
import com.HumanResourceManagement.Leave.Repository.LeaveRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeaveService {
    final private LeaveRepository leaveRepository;
    final private EmployeeRepository employeeRepository;
    final private LeaveMapper leaveMapper;

}
