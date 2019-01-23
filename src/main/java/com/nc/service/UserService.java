package com.nc.service;


import com.nc.entity.Location;
import com.nc.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User signupUser(User user);

    User getCurrentUser();

    boolean hasRole(String role);

    void updateProfile(User user, String username, String email, Location location, String password);
}
