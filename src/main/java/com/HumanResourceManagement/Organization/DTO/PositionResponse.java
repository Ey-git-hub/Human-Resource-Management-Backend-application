package com.hrms.organization.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PositionResponse {

    private Long id;

    private Long departmentId;
    private Long jobGradeId;
    private String title;
    private String code;
    private String description;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
