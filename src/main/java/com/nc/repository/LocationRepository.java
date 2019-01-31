package com.nc.repository;

import com.nc.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Null;
import java.util.Collection;
import java.util.List;

@Repository
public interface LocationRepository  extends JpaRepository<Location, Integer> {

    Location findById(Integer id);

}
