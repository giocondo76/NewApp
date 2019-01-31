package com.nc.repository;

import com.nc.entity.Change;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeRepository extends JpaRepository<Change, Integer> {

    Change findByLocationId(Integer id);
}
