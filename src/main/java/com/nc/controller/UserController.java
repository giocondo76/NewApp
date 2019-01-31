package com.nc.controller;

import com.nc.entity.Location;
import com.nc.entity.User;
import com.nc.repository.LocationRepository;
import com.nc.repository.UserVoteRepository;
import com.nc.service.UserService;
import com.nc.valid.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserVoteRepository userVoteRepository;

    @GetMapping("/profile")
    public String getProfile(Map<String, Object> model) {

        User user = userService.getCurrentUser();
        if (user.getLocation() == null) {
            model.put("user", user);
            model.put("location", new Location());
            return "profile";
        }
        else{
            model.put("user", user);
            model.put("location", locationRepository.findOne(user.getLocation().getId()));
        }
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String updateProfile(Map<String, Object> model) {
        User user = userService.getCurrentUser();

        model.put("newUser", new UserDto());
        model.put("user", user);
        model.put("locations", locationRepository.findAll());
        return "profile/edit";
    }

    @PostMapping("/profile/edit")
    public String updateProfile(
            @Valid @ModelAttribute("userForm") UserDto userDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "profile/edit";
        }

        User user = userService.getCurrentUser();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        if(userVoteRepository.findByUserId(user.getId()) != null)
        {
            userVoteRepository.delete(userVoteRepository.findByUserId(user.getId()));
        }

        userService.updateProfile(user, userDto);

        return "redirect:/profile";
    }

}
