package com.HumanResourceManagement.application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;

import com.HumanResourceManagement.application.model.AttendanceStatus;
import com.HumanResourceManagement.application.model.Employee;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class AttendanceResponse {
    private Long id;
    private Employee employee;
    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private AttendanceStatus status;
    private LocalDateTime createdAt;

}
