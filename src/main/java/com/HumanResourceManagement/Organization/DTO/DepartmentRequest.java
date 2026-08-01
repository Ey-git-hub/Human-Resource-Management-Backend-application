package com.HumanResourceManagement.Organization.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentRequest {

    @NotBlank(message = "Department name is required")
    private String name;

    private String Description;
    private String ManagerName;
}
