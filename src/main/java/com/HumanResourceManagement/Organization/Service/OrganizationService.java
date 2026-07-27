package com.HumanResourceManagement.Organization.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationResponseDto create(OrganizationRequestDto dto) {
        Organization entity = Organization.builder()
                .name(dto.getName())
                .legalName(dto.getLegalName())
                .registrationNumber(dto.getRegistrationNumber())
                .taxId(dto.getTaxId())
                .industry(dto.getIndustry())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .website(dto.getWebsite())
                .logoUrl(dto.getLogoUrl())
                .foundedDate(dto.getFoundedDate())
                .build();

        Organization saved = organizationRepository.save(entity);
        return toResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public OrganizationResponseDto getById(Long id) {
        Organization entity = organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found with id: " + id));
        return toResponseDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationResponseDto> getAll() {
        return organizationRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationResponseDto update(Long id, OrganizationRequestDto dto) {
        Organization entity = organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found with id: " + id));
        entity.setName(dto.getName());
        entity.setLegalName(dto.getLegalName());
        entity.setRegistrationNumber(dto.getRegistrationNumber());
        entity.setTaxId(dto.getTaxId());
        entity.setIndustry(dto.getIndustry());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setWebsite(dto.getWebsite());
        entity.setLogoUrl(dto.getLogoUrl());
        entity.setFoundedDate(dto.getFoundedDate());

        Organization saved = organizationRepository.save(entity);
        return toResponseDto(saved);
    }

    @Override
    public void delete(Long id) {
        if (!organizationRepository.existsById(id)) {
            throw new RuntimeException("Organization not found with id: " + id);
        }
        organizationRepository.deleteById(id);
    }

    private OrganizationResponseDto toResponseDto(Organization entity) {
        return OrganizationResponseDto.builder()
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
