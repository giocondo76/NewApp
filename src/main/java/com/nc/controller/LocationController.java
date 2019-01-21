package com.nc.controller;


import com.nc.entity.Device;
import com.nc.entity.Location;
import com.nc.entity.User;
import com.nc.repository.*;
import com.nc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.convert.ConversionService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

@Controller
public class LocationController {


    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ConversionService conversionService;

    @Autowired
    private StandartRepository standartRepository;

     @Autowired
     private LocTypeRepository locTypeRepository;

     @Autowired
    private UserService userService;


    @GetMapping("/addlocation")
    public String add(ModelMap model) {

        model.addAttribute("location", new Location());
        model.addAttribute("locTypes", locTypeRepository.findAll());
        model.addAttribute("standarts", standartRepository.findAll());
        return "addlocation";
    }

    @PostMapping("/addlocation")
    public String add(@Valid Location location, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("locTypes", locTypeRepository.findAll());
            model.addAttribute("standarts", standartRepository.findAll());
            return "addlocation";
        }
        location.setUser(userService.getCurrentUser());
        locationRepository.save(location);

        return "redirect:/index";
    }


}
