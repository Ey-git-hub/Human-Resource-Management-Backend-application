package com.hrms.organization.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobGradeResponse {

    private Long id;

    private String name;
    private Integer level;
    private String description;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
