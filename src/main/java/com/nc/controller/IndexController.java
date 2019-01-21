package com.nc.controller;

import com.nc.entity.Location;
import com.nc.entity.Role;
import com.nc.repository.LocationRepository;
import com.nc.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller

public class IndexController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(value = {"/", "/index"})
    public String index(Map<String, Object> model){

        List<Location> locations = locationRepository.findAll();
        List<Role> roles = roleRepository.findAll();
        model.put("locations", locations);
        return "index";

    }

    @PostMapping(value = {"/", "/index"})
    public String searchloc(@RequestParam Integer locId, Map<String, Object> model) {
        Iterable<Location> locations;

        if (locId != null) {
            locations = Collections.singleton(locationRepository.findById(locId));
        } else {
            locations = locationRepository.findAll();

        }
        model.put("locations", locations);
        return "index";
    }
}
