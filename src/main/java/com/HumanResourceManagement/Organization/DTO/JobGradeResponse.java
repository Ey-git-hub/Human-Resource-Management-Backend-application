package com.HumanResourceManagement.Organization.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data

@NoArgsConstructor
public class JobGradeResponse {

    private Long id;
    private String name;
    private int level;
    private String description;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
}