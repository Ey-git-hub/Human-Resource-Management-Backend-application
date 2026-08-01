package com.HumanResourceManagement.Organization.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Organization.DTO.BranchRequest;
import com.HumanResourceManagement.Organization.DTO.BranchResponse;
import com.HumanResourceManagement.Organization.Model.Branch;
import com.HumanResourceManagement.Organization.Model.Organization;

@Component
public class BranchMapper {

    public BranchResponse toResponse(Branch branch) {
        return BranchResponse.fromEntity(branch);
    }

    public Branch toEntity(BranchRequest request, Organization organization) {
        return request.toEntity(organization);
    }

    public void updateEntity(Branch existing, BranchRequest request, Organization organization) {
        if (organization != null) {
            existing.setOrganization(organization);
        }
        existing.setName(request.getName());
        existing.setCode(request.getCode());
        existing.setAddress(request.getAddress());
        existing.setCity(request.getCity());
        existing.setRegion(request.getRegion());
        existing.setCountry(request.getCountry());
        existing.setPhone(request.getPhone());
        existing.setEmail(request.getEmail());
        existing.setHeadquarters(Boolean.TRUE.equals(request.getHeadquarters()));
        if (request.getStatus() != null) {
            existing.setStatus(Branch.Status.valueOf(request.getStatus().toUpperCase()));
        }
    }
}
