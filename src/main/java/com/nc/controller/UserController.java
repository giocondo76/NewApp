package com.nc.controller;

import com.nc.entity.Location;
import com.nc.entity.User;
import com.nc.repository.LocationRepository;
import com.nc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {



    @Autowired
    private UserService userService;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String username,
            @RequestParam String email
    ) {
        userService.updateProfile(user, username, email);

        return "redirect:/user/profile";
    }
}
