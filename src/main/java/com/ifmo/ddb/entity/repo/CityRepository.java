package com.ifmo.ddb.entity.repo;

import com.ifmo.ddb.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    List<City> findAllByIdIn(List<Integer> ids);

}
