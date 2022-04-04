package com.ifmo.ddb.entity.repo;

import com.ifmo.ddb.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Integer> {
}
