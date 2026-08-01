package com.HumanResourceManagement.Organization.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.HumanResourceManagement.Organization.Model.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    Page<Branch> findByOrganizationId(Long organizationId, Pageable pageable);
}
