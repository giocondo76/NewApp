package com.nc.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="dev_type")
public class DevType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dt_id")
    private Integer id;

    @Column(name = "name")
    private String name;


    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "devType")
    private List<Device> devices;

    public DevType() {
    }

    public DevType(Integer id, String name, List<Device> devices) {
        this.id = id;
        this.name = name;
        this.devices = devices;
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

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

}
