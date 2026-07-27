package com.HumanResourceManagement.Organization.DTO;

import lombok.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrganizationRequest {

    @NotBlank(message = "name is required")
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
}
