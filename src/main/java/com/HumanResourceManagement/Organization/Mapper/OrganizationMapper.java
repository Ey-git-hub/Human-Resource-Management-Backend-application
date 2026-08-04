package com.HumanResourceManagement.Organization.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Organization.DTO.OrganizationRequest;
import com.HumanResourceManagement.Organization.DTO.OrganizationResponse;
import com.HumanResourceManagement.Organization.Model.Organization;
import com.HumanResourceManagement.shared.util.MapperUtils;

@Component
public class OrganizationMapper {

    public OrganizationResponse toResponse(Organization organization) {
        return MapperUtils.map(organization, OrganizationResponse.class);
    }

    public Organization toEntity(OrganizationRequest request) {
        return MapperUtils.map(request, Organization.class);
    }

    public void updateEntity(Organization existing, OrganizationRequest request) {
        MapperUtils.copy(request, existing);
    }
}
