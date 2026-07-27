package com.HumanResourceManagement.Organization.DTO;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

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
