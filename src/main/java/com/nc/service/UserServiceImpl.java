package com.nc.service;

import com.nc.controller.response.EmailExistsException;
import com.nc.controller.response.UsernameExistsException;
import com.nc.entity.Location;
import com.nc.entity.Role;
import com.nc.entity.User;
import com.nc.repository.RoleRepository;
import com.nc.repository.UserRepository;
import com.nc.valid.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final long ROLE_USER_ID = 1L;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    private UserDto userDto;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Can't find user with username " + username);
        }
        return user;
    }

    @Override
    public User registerNewUserAccount(User user) throws EmailExistsException, UsernameExistsException {
        if (emailExist(user.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress: "
                            +  user.getEmail());
        }

        if (usernameExist(user.getUsername())) {
            throw new UsernameExistsException(
                    "There is an account with that Username: "
                            +  user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findOne((int) ROLE_USER_ID);
        user.setRoles(Collections.singleton(userRole));
        return userRepository.save(user);
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }


    private boolean usernameExist(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        User user = null;
        if (userDetails instanceof User) {
            user = (User) userDetails;
        }
        return user;
    }

    @Override
    public boolean hasRole(String role) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            hasRole = authority.getAuthority().equals(role);
            if (hasRole) {
                break;
            }
        }
        return hasRole;
    }

    @Override
    public void updateProfile(User user, String username, String email, Location location, String password) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));

        if (isEmailChanged) {
            user.setEmail(email);
        }

        if (!StringUtils.isEmpty(username)) {
            user.setUsername(username);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLocation(location);
        userRepository.save(user);

    }


}