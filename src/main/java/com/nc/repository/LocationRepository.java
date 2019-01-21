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
//    List<Location> findByDeviceIsNull(@Param("loc_id") Integer id);
//    @Query("select location from Location location join c.addresses a where (a.city = :cityName)")
//    List<Customer> findByCity(@Param("cityName")String city);

//    List<Location> findByDevices(Collection.singleton();

}
