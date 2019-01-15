package com.nc.repository;

import com.nc.entity.Standart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StandartRepository extends JpaRepository<Standart, Integer> {

}