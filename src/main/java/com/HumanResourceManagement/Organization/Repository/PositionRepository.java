package com.HumanResourceManagement.Organization.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// import com.HumanResourceManagement.Organization.Model.Branch;
import com.HumanResourceManagement.Organization.Model.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {
    Page<Position> findByDepartmentId(Long departmentId, Pageable pageable);

}
