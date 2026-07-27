package com.HumanResourceManagement.Organization.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HumanResourceManagement.Organization.Model.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
