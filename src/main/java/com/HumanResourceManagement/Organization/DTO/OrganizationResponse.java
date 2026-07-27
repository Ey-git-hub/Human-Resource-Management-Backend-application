package com.HumanResourceManagement.Organization.DTO;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.HumanResourceManagement.Organization.Model.Organization;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    private OrganizationResponse fromEntiity(Organization entity) {
        return OrganizationResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .legalName(entity.getLegalName())
                .registrationNumber(entity.getRegistrationNumber())
                .taxId(entity.getTaxId())
                .industry(entity.getIndustry())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .website(entity.getWebsite())
                .logoUrl(entity.getLogoUrl())
                .foundedDate(entity.getFoundedDate())

                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
