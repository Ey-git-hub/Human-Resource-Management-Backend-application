package com.hrms.organization.dto;

import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PositionRequest {

    @NotNull(message = "departmentId is required")
    private Long departmentId;
    @NotNull(message = "jobGradeId is required")
    private Long jobGradeId;
    @NotBlank(message = "title is required")
    private String title;
    private String code;
    private String description;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    @NotBlank(message = "status is required")
    private String status;
}
