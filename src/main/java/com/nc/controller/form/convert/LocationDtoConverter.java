package com.nc.controller.form.convert;

import com.nc.controller.form.LocationDto;
import com.nc.entity.Location;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocationDtoConverter implements Converter<Location, LocationDto> {


    @Override
    public LocationDto convert(Location location) {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(location.getId());
        locationDto.setName(location.getName());
        locationDto.setTypeId(location.getLocType().getId());
        locationDto.setStandartId(location.getStandart().getId());
        locationDto.setStandartId(location.getStandart().getId());
        return locationDto;
    }
}
