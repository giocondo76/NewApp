package com.nc.controller;

import com.nc.entity.Location;
import com.nc.entity.LocationFlag;
import com.nc.repository.LocationRepository;
import com.nc.repository.RoleRepository;
import com.nc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;


    @GetMapping(value={"/","/index"})
    public String index(Map<String, Object> model){

        List<Location> locations = locationRepository.findAll();
        List<LocationFlag> locationFlags = createListOfLocationFlags(locations);
        model.put("locations", locations);
        model.put("locationFlags",locationFlags);
        return "index";
    }

    public List<LocationFlag> createListOfLocationFlags(List<Location> locations){

        List<String> flags = new ArrayList<>();
        flags.add("Room manager");
        flags.add("User");
        List<LocationFlag> locationFlags = new ArrayList<>();
        for(Location location:locations){
            if(location.getUser().getId().equals(userService.getCurrentUser().getId())){
                locationFlags.add(new LocationFlag(location,flags.get(0)));
            }
            else
                locationFlags.add(new LocationFlag(location,flags.get(1)));
        }
        return locationFlags;
    }
}
