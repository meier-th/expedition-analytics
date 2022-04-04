package com.ifmo.ddb.entity.repo;

import com.ifmo.ddb.entity.Expedition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpeditionRepository extends JpaRepository<Expedition, Integer> {
}
