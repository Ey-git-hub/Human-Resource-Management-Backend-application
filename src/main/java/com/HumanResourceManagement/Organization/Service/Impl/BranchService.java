package com.HumanResourceManagement.Organization.Service.Impl;

import com.HumanResourceManagement.Organization.Model.Branch;
import com.HumanResourceManagement.Organization.Model.Organization;
import com.HumanResourceManagement.Organization.DTO.BranchRequest;
import com.HumanResourceManagement.Organization.DTO.BranchResponse;
import com.HumanResourceManagement.Organization.Mapper.BranchMapper;
import com.HumanResourceManagement.Organization.Repository.BranchRepository;
import com.HumanResourceManagement.Organization.Repository.OrganizationRepository;
import com.HumanResourceManagement.Organization.Service.BranchServiceInterface;
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
public class BranchService implements BranchServiceInterface {

    private final BranchRepository branchRepository;
    private final OrganizationRepository organizationRepository;
    private final BranchMapper branchMapper;

    @Override
    public BranchResponse createBranch(BranchRequest requestDto) {
        Organization organization = organizationRepository.findById(requestDto.getOrganizationId())
                .orElseThrow(() -> ResourceNotFoundException.of("Organization", requestDto.getOrganizationId()));

        Branch branch = branchMapper.toEntity(requestDto, organization);
        Branch savedBranch = branchRepository.save(branch);

        return branchMapper.toResponse(savedBranch);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BranchResponse> getBranchById(Long id) {
        return branchRepository.findById(id).map(branchMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BranchResponse> getAllBranches(Pageable pageable) {
        return branchRepository.findAll(pageable).map(branchMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BranchResponse> getBranchesByOrganization(Long organizationId, Pageable pageable) {
        return branchRepository.findByOrganizationId(organizationId, pageable).map(branchMapper::toResponse);
    }

    @Override
    public BranchResponse updateBranch(Long id, BranchRequest requestDto) {
        Branch existingBranch = branchRepository.findById(id)
            .orElseThrow(() -> ResourceNotFoundException.of("Branch", id));

        Organization organization = organizationRepository.findById(requestDto.getOrganizationId())
            .orElseThrow(() -> ResourceNotFoundException.of("Organization", requestDto.getOrganizationId()));

        branchMapper.updateEntity(existingBranch, requestDto, organization);

        Branch updatedBranch = branchRepository.save(existingBranch);
        return branchMapper.toResponse(updatedBranch);
    }

    @Override
    public void deleteBranch(Long id) {
        if (!branchRepository.existsById(id)) {
            throw ResourceNotFoundException.of("Branch", id);
        }
        branchRepository.deleteById(id);
    }
}