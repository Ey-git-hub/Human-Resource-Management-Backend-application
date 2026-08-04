package com.HumanResourceManagement.Organization.Service.Impl;

import com.HumanResourceManagement.Organization.Model.Organization;
import com.HumanResourceManagement.Organization.DTO.OrganizationRequest;
import com.HumanResourceManagement.Organization.DTO.OrganizationResponse;
import com.HumanResourceManagement.Organization.Mapper.OrganizationMapper;
import com.HumanResourceManagement.Organization.Repository.OrganizationRepository;
import com.HumanResourceManagement.Organization.Service.OrganizationServiceInterface;
import com.HumanResourceManagement.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrganizationService implements OrganizationServiceInterface {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    @Override
    public OrganizationResponse createOrganization(OrganizationRequest requestDto) {
        Organization organization = organizationMapper.toEntity(requestDto);
        Organization savedOrganization = organizationRepository.save(organization);
        return organizationMapper.toResponse(savedOrganization);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrganizationResponse> getOrganizationById(Long id) {
        return organizationRepository.findById(id).map(organizationMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrganizationResponse> getAllOrganizations(Pageable pageable) {
        return organizationRepository.findAll(pageable).map(organizationMapper::toResponse);
    }

    @Override
    public OrganizationResponse updateOrganization(Long id, OrganizationRequest requestDto) {
        Organization existingOrganization = organizationRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("Organization", id));

        organizationMapper.updateEntity(existingOrganization, requestDto);

        Organization updatedOrganization = organizationRepository.save(existingOrganization);
        return organizationMapper.toResponse(updatedOrganization);
    }

    @Override
    public void deleteOrganization(Long id) {
        if (!organizationRepository.existsById(id)) {
            throw ResourceNotFoundException.of("Organization", id);
        }
        organizationRepository.deleteById(id);
    }
}