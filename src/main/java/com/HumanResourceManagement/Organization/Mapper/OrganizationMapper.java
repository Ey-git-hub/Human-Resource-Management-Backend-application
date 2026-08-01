package com.HumanResourceManagement.Organization.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Organization.DTO.OrganizationRequest;
import com.HumanResourceManagement.Organization.DTO.OrganizationResponse;
import com.HumanResourceManagement.Organization.Model.Organization;

@Component
public class OrganizationMapper {

    public OrganizationResponse toResponse(Organization organization) {
        return OrganizationResponse.fromEntity(organization);
    }

    public Organization toEntity(OrganizationRequest request) {
        return request.toEntity();
    }

    public void updateEntity(Organization existing, OrganizationRequest request) {
        existing.setName(request.getName());
        existing.setLegalName(request.getLegalName());
        existing.setRegistrationNumber(request.getRegistrationNumber());
        existing.setTaxId(request.getTaxId());
        existing.setIndustry(request.getIndustry());
        existing.setAddress(request.getAddress());
        existing.setPhone(request.getPhone());
        existing.setEmail(request.getEmail());
        existing.setWebsite(request.getWebsite());
        existing.setLogoUrl(request.getLogoUrl());
        existing.setFoundedDate(request.getFoundedDate());
    }
}
