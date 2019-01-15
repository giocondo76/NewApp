package com.nc.repository;


import com.nc.entity.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConditionRepository extends JpaRepository< Condition, Integer> {
    Condition getById(Integer id);

    List<Condition> findByLocationId(@Param("loc_id") Integer id);
}