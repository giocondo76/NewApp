package com.nc.service;


import com.nc.controller.response.EmailExistsException;
import com.nc.controller.response.UsernameExistsException;
import com.nc.entity.Location;
import com.nc.entity.User;
import com.nc.valid.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User registerNewUserAccount(User user) throws EmailExistsException, UsernameExistsException, UsernameExistsException;

    User getCurrentUser();

    boolean hasRole(String role);

    void updateProfile(User user, UserDto userDto);
}
