package com.nc.controller.form.convert;

import com.nc.controller.form.LocationDto;
import com.nc.entity.*;
import com.nc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocationConverter implements Converter<LocationDto, Location> {


    private StandartRepository standartRepository;


    private LocTypeRepository locTypeRepository;



    @Override
    public Location convert(LocationDto locationDto) {

        Location location = new Location();
        location.setId(locationDto.getId());
        location.setName(locationDto.getName());

        LocType locType = locTypeRepository.findOne(locationDto.getTypeId());
        Standart standart = standartRepository.findOne(locationDto.getStandartId());

        location.setLocType(locType);
        location.setStandart(standart);


        return location;
    }
}
