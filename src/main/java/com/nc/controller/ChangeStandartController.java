package com.nc.controller;

import com.nc.entity.Change;
import com.nc.entity.Device;
import com.nc.entity.Location;
import com.nc.entity.LocationFlag;
import com.nc.repository.ChangeRepository;
import com.nc.repository.LocationRepository;
import com.nc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ChangeStandartController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ChangeRepository changeRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/standart/{id}/edit")
    public String changeStandart(@PathVariable Integer id, Map<String, Object> model) {


        Location location= locationRepository.findOne(id);
        LocationFlag locationFlag = new LocationFlag();
        if(location.getUser().getId().equals(userService.getCurrentUser().getId())){
            locationFlag = new LocationFlag(location,"Admin");
        }
        else
            locationFlag = new LocationFlag(location,"User");
        model.put("locationFlag",locationFlag);
        model.put("change", new Change());
        return "standart/edit";
    }
//
    @PostMapping("/standart/{id}/edit")
    public String changeStandart(@PathVariable Integer id, @Valid Change change, BindingResult result) {
        if (result.hasErrors()) {
            return "/standart/edit";
        }
        change.setLocation(locationRepository.findById(id));
        change.setTimestamp(new Timestamp(System.currentTimeMillis()));
        changeRepository.save(change);

        return "redirect:/condition/{id}";
    }


}
