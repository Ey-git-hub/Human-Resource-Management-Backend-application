package com.HumanResourceManagement.Organization.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data

@NoArgsConstructor
public class OrganizationResponse {

    private Long id;
    private String name;
    private String legalName;
    private String registrationNumber;
    private String taxId;
    private String industry;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String logoUrl;
    private LocalDate foundedDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}