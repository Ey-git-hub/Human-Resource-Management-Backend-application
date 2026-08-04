package com.HumanResourceManagement.Organization.Service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.HumanResourceManagement.Organization.DTO.BranchRequest;
import com.HumanResourceManagement.Organization.DTO.BranchResponse;

public interface BranchServiceInterface {
    BranchResponse createBranch(BranchRequest requestDto);

    Optional<BranchResponse> getBranchById(Long id);

    Page<BranchResponse> getAllBranches(Pageable pageable);

    Page<BranchResponse> getBranchesByOrganization(Long organizationId, Pageable pageable);

    BranchResponse updateBranch(Long id, BranchRequest requestDto);

    void deleteBranch(Long id);
}
