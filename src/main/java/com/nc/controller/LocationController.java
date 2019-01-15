package com.nc.controller;

import com.nc.controller.form.LocationDto;
import com.nc.entity.Location;
import com.nc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.convert.ConversionService;

import javax.validation.Valid;

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

    @GetMapping("/addlocation")
    public String add(ModelMap model) {

        model.addAttribute("location", new LocationDto());
        model.addAttribute("Types", locTypeRepository.findAll());
        model.addAttribute("Standarts", standartRepository.findAll());
        return "addlocation";
    }

    @PostMapping("/addlocation")
    public String add(@Valid @ModelAttribute("location") LocationDto locationDto,
                      BindingResult result, ModelMap model) {
        if (result.hasErrors()) {

            model.addAttribute("types", locTypeRepository.findAll());
            model.addAttribute("standarts", standartRepository.findAll());
            return "addlocation";
        }

        Location location = conversionService.convert(locationDto, Location.class);

        locationRepository.save(location);

        return "/location";
    }


}
