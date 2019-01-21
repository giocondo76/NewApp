package com.nc.repository;

import com.nc.entity.DevType;
import com.nc.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevTypeRepository extends JpaRepository<DevType, Integer> {
}
