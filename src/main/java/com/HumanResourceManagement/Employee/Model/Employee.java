package com.HumanResourceManagement.Employee.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.HumanResourceManagement.Organization.Model.Department;

// import com.HumanResourceManagement.application.model.Organization.Department;

// import com.HumanResourceManagement.application.model.Organization.Department;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "employee_table")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    private String phoneNumber;
    private String JobTitle;

    // combining department entity to employee
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    private Double salary;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeStatus status = EmployeeStatus.ACTIVE;
    @Column(updatable = false)
    private LocalDate hireDate;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
