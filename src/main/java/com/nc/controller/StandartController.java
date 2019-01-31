package com.nc.controller;

import com.nc.entity.*;
import com.nc.repository.*;
import com.nc.service.StandartService;
import com.nc.service.UserService;
import com.nc.service.UserVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.*;

@Controller
public class StandartController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ChangeRepository changeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private StandartRepository standartRepository;

    @Autowired
    private UserVoteRepository userVoteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserVoteService userVoteService;

    @Autowired
    private StandartService standartService;

    @GetMapping("/standart/{id}/edit")
    public String changeStandartOfLocation(@PathVariable Integer id, Map<String, Object> model) {
        Location location = locationRepository.findOne(id);
        LocationFlag locationFlag = createNewFlag(location);
        model.put("locationFlag", locationFlag);
        model.put("change", new Change());
        return "standart/edit";
    }

    @PostMapping("/standart/{id}/edit")
    public String editStandartOfLocation(@PathVariable Integer id, @Valid Change change, BindingResult result) {
        if (result.hasErrors()) {
            return "/standart/edit";
        }
        if (changeRepository.findByLocationId(id) == null) {
            change.setLocation(locationRepository.findById(id));
            changeRepository.save(change);
            saveOrDeleteStandartAfterThreeHours(change, locationRepository.findById(id));
        } else {
            changeRepository.delete(changeRepository.findByLocationId(id));
            List<User> users = userRepository.findByLocationId(id);
            for (User user : users) {
                if (userVoteRepository.findByUserId(user.getId()) != null) {
                    userVoteRepository.delete(userVoteRepository.findByUserId(user.getId()));
                }
            }
            change.setLocation(locationRepository.findById(id));
            changeRepository.save(change);
            saveOrDeleteStandartAfterThreeHours(change, locationRepository.findById(id));
        }
        return "redirect:/condition/{id}";
    }

    @GetMapping("/standart/all")
    public String listOfStandarts(Map<String, Object> model) {
        List<Standart> standarts = standartRepository.findAll();
        model.put("standarts", standarts);

        return "standart/all";
    }

    @GetMapping("/standart/add")
    public String addStandart(Map<String, Object> model) {

        model.put("standart", new Standart());
        return "standart/add";
    }

    @PostMapping("/standart/add")
    public String addStandart(@Valid Standart standart, BindingResult result) {
        if (result.hasErrors()) {
            return "standart/add";
        }
        standartRepository.save(standart);

        return "redirect:/standart/all";
    }

    public LocationFlag createNewFlag(Location location){
        List<String> flags = new ArrayList<>();
        flags.add("Room manager");
        flags.add("User");
        LocationFlag locationFlag;
        if(location.getUser().getId().equals(userService.getCurrentUser().getId())){
            locationFlag = new LocationFlag(location,flags.get(0));
        }
        else {
            locationFlag = new LocationFlag(location, flags.get(1));
        }
        return  locationFlag;
    }

    public void saveOrDeleteStandartAfterThreeHours(Change change, Location location) {
        TimerTask task = new TimerTask() {
            public void run() {
                if (userVoteService.showVotingResult(location) >= 50) {
                    Standart standart = standartService.addNewStandart(change);
                    standartRepository.save(standart);
                    location.setStandart(standart);
                }
                changeRepository.delete(change.getId());
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 10800000);
    }
}
