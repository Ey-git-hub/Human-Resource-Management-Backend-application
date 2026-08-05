package com.HumanResourceManagement.Organization.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Organization.DTO.BranchRequest;
import com.HumanResourceManagement.Organization.DTO.BranchResponse;
import com.HumanResourceManagement.Organization.Model.Branch;
import com.HumanResourceManagement.Organization.Model.Organization;
import com.HumanResourceManagement.shared.util.MapperUtils;

@Component
public class BranchMapper {

    public BranchResponse toResponse(Branch branch) {
        BranchResponse response = MapperUtils.map(branch, BranchResponse.class);
        if (branch.getOrganization() != null) {
            response.setOrganizationId(branch.getOrganization().getId());
            response.setOrganizationName(branch.getOrganization().getName());
        }
        return response;
    }

    public Branch toEntity(BranchRequest request, Organization organization) {
        Branch branch = MapperUtils.map(request, Branch.class);
        branch.setOrganization(organization);
        branch.setHeadquarters(Boolean.TRUE.equals(request.getHeadquarters()));
        if (request.getStatus() != null) {
            branch.setStatus(Branch.Status.valueOf(request.getStatus().toUpperCase()));
        }
        return branch;
    }

    public void updateEntity(Branch existing, BranchRequest request, Organization organization) {
        MapperUtils.copy(request, existing);
        if (organization != null) {
            existing.setOrganization(organization);
        }
        existing.setHeadquarters(Boolean.TRUE.equals(request.getHeadquarters()));
        if (request.getStatus() != null) {
            existing.setStatus(Branch.Status.valueOf(request.getStatus().toUpperCase()));
        }
    }
}
