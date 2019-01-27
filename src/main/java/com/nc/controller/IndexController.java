package com.nc.controller;

import com.nc.entity.Location;
import com.nc.entity.LocationFlag;
import com.nc.repository.LocationRepository;
import com.nc.repository.RoleRepository;
import com.nc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

        List<LocationFlag> locationFlags = new ArrayList<>();
        List<Location> locations = locationRepository.findAll();
        for(Location location:locations){
            if(location.getUser().getId().equals(userService.getCurrentUser().getId())){
                  locationFlags.add(new LocationFlag(location,"Admin"));
            }
            else
                locationFlags.add(new LocationFlag(location,"User"));
        }
        model.put("locations", locations);
        model.put("locationFlags",locationFlags);
        return "index";

    }

    @GetMapping(value={"/index"})
    public String searchLocationById(@RequestParam Integer search, Map<String, Object> model) {
        Iterable<Location> locations;

        if (search != null) {
            locations = Collections.singleton(locationRepository.findById(search));
        } else {
            locations = locationRepository.findAll();

        }
        model.put("locations", locations);
        return "index";
    }

//    @GetMapping(value={"/","/index"})
//    public String showStudentBySurname(@RequestParam (value = "surname", required = false) String surname, Model model) {
//        model.addAttribute("search", studentService.listStudentsBySurname(surname));
//        return "students";
//    }
}
