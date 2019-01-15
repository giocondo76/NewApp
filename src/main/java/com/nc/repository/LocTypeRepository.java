package com.nc.repository;

import com.nc.entity.LocType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocTypeRepository extends JpaRepository<LocType, Integer> {

}