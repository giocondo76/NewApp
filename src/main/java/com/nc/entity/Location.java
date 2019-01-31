package com.nc.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @SequenceGenerator(name = "location_id_sequence_gen",
            sequenceName="location_id_sequence", initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_id_sequence_gen")
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

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "location", cascade = {CascadeType.ALL})
    private List<User> users;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "location", cascade = {CascadeType.ALL})
    private List<Change> changes;

    public Location(Integer id, String name, List<Device> devices, List<Condition> conditions,
                    LocType locType, Standart standart, User user, List<User> users, List<Change> changes) {
        this.id = id;
        this.name = name;
        this.devices = devices;
        this.conditions = conditions;
        this.locType = locType;
        this.standart = standart;
        this.user = user;
        this.users = users;
        this.changes = changes;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Change> getChanges() {
        return changes;
    }

    public void setChanges(List<Change> changes) {
        this.changes = changes;
    }
}

