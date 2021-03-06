package com.nc.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "standart")
public class Standart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "st_id")
    private Integer id;


    @Column(name = "name")
    private String name;


    @Column(name = "temp_min")
    private Integer temp_min;


    @Column(name = "temp_max")
    private Integer temp_max;


    @Column(name = "hum_max")
    private Integer hum_max;


    @Column(name = "carb_diox_min")
    private Integer co2_min;


    @Column(name = "carb_diox_max")
    private Integer co2_max;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "standart")
    private List<Location> locations;

    public Standart() {
    }

    public Standart(Integer id, String name, Integer temp_min, Integer temp_max, Integer hum_max, Integer co2_min, Integer co2_max, List<Location> locations) {
        this.id = id;
        this.name = name;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.hum_max = hum_max;
        this.co2_min = co2_min;
        this.co2_max = co2_max;
        this.locations = locations;
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

    public Integer getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(Integer temp_min) {
        this.temp_min = temp_min;
    }

    public Integer getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(Integer temp_max) {
        this.temp_max = temp_max;
    }

    public Integer getHum_max() {
        return hum_max;
    }

    public void setHum_max(Integer hum_max) {
        this.hum_max = hum_max;
    }

    public Integer getCo2_min() {
        return co2_min;
    }

    public void setCo2_min(Integer co2_min) {
        this.co2_min = co2_min;
    }

    public Integer getCo2_max() {
        return co2_max;
    }

    public void setCo2_max(Integer co2_max) {
        this.co2_max = co2_max;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}