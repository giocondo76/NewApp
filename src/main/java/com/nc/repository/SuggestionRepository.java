package com.nc.repository;

import com.nc.entity.Condition;
import com.nc.entity.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {

    List<Suggestion> findByLocationId(@Param("loc_id") Integer id);
}
