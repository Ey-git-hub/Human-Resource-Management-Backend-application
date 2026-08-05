package com.HumanResourceManagement.Organization.DTO;

import com.HumanResourceManagement.Organization.Model.Position.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data

@NoArgsConstructor
public class PositionResponse {

    private Long id;
    private Long departmentId;
    private String departmentName;
    private Long jobGradeId;
    private String jobGradeName;
    private String title;
    private String code;
    private String description;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private Status status;
}