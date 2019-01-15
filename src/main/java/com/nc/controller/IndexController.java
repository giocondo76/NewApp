package com.nc.controller;

import com.nc.entity.Location;
import com.nc.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    LocationRepository locationRepository;

    @GetMapping
    public String index(Map<String, Object> model){

        List<Location> locations = locationRepository.findAll();
        model.put("locations", locations);
        return "index";

    }
}
