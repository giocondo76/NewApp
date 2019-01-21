package com.nc.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "loc_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "location")
    private List<Device> devices;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "location")
    private List<Condition> conditions;


    @ManyToOne
    @JoinColumn(name = "type")
    private LocType locType;

    @ManyToOne
    @JoinColumn(name = "standart")
    private Standart standart;

    @ManyToOne
    @JoinColumn(name = "room_manager")
    private User user;

    public Location(Integer id, String name, List<Device> devices, List<Condition> conditions,
                    List<User> users, LocType locType, Standart standart, User user) {
        this.id = id;
        this.name = name;
        this.devices = devices;
        this.conditions = conditions;
        this.locType = locType;
        this.standart = standart;
        this.user = user;
    }

    public Location() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public LocType getLocType() {
        return locType;
    }

    public void setLocType(LocType locType) {
        this.locType = locType;
    }

    public Standart getStandart() {
        return standart;
    }

    public void setStandart(Standart standart) {
        this.standart = standart;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

