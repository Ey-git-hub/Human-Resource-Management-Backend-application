package com.HumanResourceManagement.Organization.DTO;

import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class JobGradeRequest {

    @NotBlank(message = "name is required")
    private String name;
    @NotNull(message = "level is required")
    private Integer level;
    private String description;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
}
