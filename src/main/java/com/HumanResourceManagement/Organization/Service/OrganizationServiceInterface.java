package com.HumanResourceManagement.Organization.Service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.HumanResourceManagement.Organization.DTO.OrganizationRequest;
import com.HumanResourceManagement.Organization.DTO.OrganizationResponse;

public interface OrganizationServiceInterface {
    OrganizationResponse createOrganization(OrganizationRequest requestDto);

    Optional<OrganizationResponse> getOrganizationById(Long id);

    Page<OrganizationResponse> getAllOrganizations(Pageable pageable);

    OrganizationResponse updateOrganization(Long id, OrganizationRequest requestDto);

    void deleteOrganization(Long id);
}
