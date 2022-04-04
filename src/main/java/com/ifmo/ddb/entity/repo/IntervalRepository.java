package com.ifmo.ddb.entity.repo;

import com.ifmo.ddb.entity.Interval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervalRepository extends JpaRepository<Interval, Integer> {
}
