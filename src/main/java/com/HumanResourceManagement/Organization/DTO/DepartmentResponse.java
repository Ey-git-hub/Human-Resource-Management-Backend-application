package com.HumanResourceManagement.Organization.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepartmentResponse {
    private Long id;
    private String name;
    private Long managerId;
    private String managerName;
    private String Description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
