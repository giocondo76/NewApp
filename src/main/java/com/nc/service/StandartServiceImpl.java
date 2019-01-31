package com.nc.service;

import com.nc.entity.Change;
import com.nc.entity.Standart;
import com.nc.repository.StandartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StandartServiceImpl implements StandartService {


    @Autowired
    private StandartRepository standartRepository;
    @Override
    public Standart addNewStandart(Change change) {
        Standart standart = new Standart();
        standart.setName(change.getName());
        standart.setTemp_min(change.getTemp_min());
        standart.setTemp_max(change.getTemp_max());
        standart.setHum_max(change.getHum_max());
        standart.setCo2_min(change.getCo2_min());
        standart.setCo2_max(change.getCo2_max());
         return standart;
    }
}
