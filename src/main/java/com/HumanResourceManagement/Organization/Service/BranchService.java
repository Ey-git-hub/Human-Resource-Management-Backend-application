package com.HumanResourceManagement.Organization.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HumanResourceManagement.Organization.DTO.BranchRequest;
import com.HumanResourceManagement.Organization.DTO.BranchResponse;
import com.HumanResourceManagement.Organization.Model.Branch;
import com.HumanResourceManagement.Organization.Model.Organization;
import com.HumanResourceManagement.Organization.Repository.BranchRepository;
import com.HumanResourceManagement.Organization.Repository.OrganizationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BranchService {

    private final BranchRepository branchRepository;
    private final OrganizationRepository organizationRepository;

    public BranchResponse create(BranchRequest dto) {
        Branch entity = Branch.builder()
                .name(dto.getName())
                .code(dto.getCode())
                .address(dto.getAddress())
                .city(dto.getCity())
                .region(dto.getRegion())
                .country(dto.getCountry())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .headquarters(dto.getHeadquarters())
                .status(dto.getStatus())
                .build();
        Organization organization = organizationRepository.findById(dto.getOrganizationId())
                .orElseThrow(() -> new RuntimeException("Organization not found with id: " + dto.getOrganizationId()));
        entity.setOrganization(organization);
        Branch saved = branchRepository.save(entity);
        return toResponseDto(saved);
    }

    @Transactional(readOnly = true)
    public BranchResponse getById(Long id) {
        Branch entity = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found with id: " + id));
        return toResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<BranchResponse> getAll() {
        return branchRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public BranchResponse update(Long id, BranchRequest dto) {
        Branch entity = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found with id: " + id));
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        entity.setAddress(dto.getAddress());
        entity.setCity(dto.getCity());
        entity.setRegion(dto.getRegion());
        entity.setCountry(dto.getCountry());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setHeadquarters(dto.getHeadquarters());
        entity.setStatus(dto.getStatus());
        Organization organization = organizationRepository.findById(dto.getOrganizationId())
                .orElseThrow(() -> new RuntimeException("Organization not found with id: " + dto.getOrganizationId()));
        entity.setOrganization(organization);
        Branch saved = branchRepository.save(entity);
        return toResponseDto(saved);
    }

    public void delete(Long id) {
        if (!branchRepository.existsById(id)) {
            throw new RuntimeException("Branch not found with id: " + id);
        }
        branchRepository.deleteById(id);
    }

    // private BranchResponse toResponseDto(Branch entity) {
    // return BranchResponse.builder()
    // .id(entity.getId())
    // .name(entity.getName())
    // .code(entity.getCode())
    // .address(entity.getAddress())
    // .city(entity.getCity())
    // .region(entity.getRegion())
    // .country(entity.getCountry())
    // .phone(entity.getPhone())
    // .email(entity.getEmail())
    // .headquarters(entity.getHeadquarters())
    // .status(entity.getStatus())
    // .organizationId(entity.getOrganization() != null ?
    // entity.getOrganization().getId() : null)
    // .createdAt(entity.getCreatedAt())
    // .updatedAt(entity.getUpdatedAt())
    // .build();
    // }
}
