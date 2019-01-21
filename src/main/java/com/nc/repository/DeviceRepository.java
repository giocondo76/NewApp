package com.nc.repository;

import com.nc.entity.Condition;
import com.nc.entity.Device;
import com.nc.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {

}
