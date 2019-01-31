package com.nc.controller;


import com.nc.entity.Device;
import com.nc.entity.Location;
import com.nc.entity.User;
import com.nc.entity.UserVote;
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

     @Autowired
     private UserVoteRepository userVoteRepository;

     @Autowired
     private UserRepository userRepository;


    @GetMapping("/location/add")
    public String addLocation(ModelMap model) {

        model.addAttribute("location", new Location());
        model.addAttribute("locTypes", locTypeRepository.findAll());
        model.addAttribute("standarts", standartRepository.findAll());
        return "location/add";
    }

    @PostMapping("/location/add")
    public String addLocation(@Valid Location location, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("locTypes", locTypeRepository.findAll());
            model.addAttribute("standarts", standartRepository.findAll());
            return "location/add";
        }
        User user = userService.getCurrentUser();
        location.setUser(userService.getCurrentUser());
        if(userVoteRepository.findByUserId(user.getId()) != null)
        {
            userVoteRepository.delete(userVoteRepository.findByUserId(user.getId()));
        }

        locationRepository.save(location);

        return "redirect:/index";
    }


}
