package com.nc.service;

import com.nc.entity.Change;
import com.nc.entity.Standart;
import org.springframework.stereotype.Service;

@Service
public interface StandartService {

    Standart addNewStandart(Change change);
}
