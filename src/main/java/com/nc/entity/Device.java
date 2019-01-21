package com.nc.entity;

import javax.persistence.*;

@Entity
@Table(name="device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "dev_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn (name="type")
    private DevType devType;

    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn (name="location")
    private Location location;

    public Device(Integer id, String name, DevType devType, Location location) {
        this.id = id;
        this.name = name;
        this.devType = devType;
        this.location = location;
    }

    public Device() {
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

    public DevType getDevType() {
        return devType;
    }

    public void setDevType(DevType devType) {
        this.devType = devType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
