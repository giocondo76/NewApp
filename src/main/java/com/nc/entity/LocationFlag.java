package com.nc.entity;

import com.nc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;

public class LocationFlag {

    @Autowired
    private UserService userService;

    private Location location;
    private String flag;

    public LocationFlag(Location location, String flag) {
        this.location = location;
        this.flag = flag;
    }

    public LocationFlag() {
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}
