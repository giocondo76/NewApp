package com.nc.controller;

import com.nc.entity.Location;
import com.nc.entity.User;
import com.nc.repository.LocationRepository;
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

    @GetMapping("/profile")
    public String getProfile(Map<String, Object> model) {
        User user = userService.getCurrentUser();
        model.put("user", user);

        return "profile";
    }

    @GetMapping("/profile/edit")
    public String updateProfile(Map<String, Object> model) {
        User user1 = userService.getCurrentUser();

        model.put("user", new UserDto());
        model.put("user1", user1);
        model.put("locations", locationRepository.findAll());
        return "profile/edit";
    }

//    @PostMapping("/profile")
//    public String updateProfile(
//            @AuthenticationPrincipal User user,
//            @RequestParam String username,
//            @RequestParam String email,
//            @RequestParam Location location) {
//
//        user.setLocation(location);
//        userService.updateProfile(user, username, email,location);
//
//
//        return "redirect:/profile";
//    }

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

        userService.updateProfile(user, userDto.getUsername(),userDto.getEmail(),userDto.getLocation(),userDto.getPassword());

        return "redirect:/";
    }

}
