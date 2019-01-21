package com.nc.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "condition")
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cond_id")
    private Integer id;

    @Column(name = "datetime")
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;

    @Column(name = "temp")
    private Integer temp;

    @Column(name = "hum")
    private Integer hum;

    @Column(name = "carb_diox")
    private Integer co2;

    public Condition(Integer id, Timestamp timestamp, Location location, Integer temp, Integer hum, Integer co2) {
        this.id = id;
        this.timestamp = timestamp;
        this.location = location;
        this.temp = temp;
        this.hum = hum;
        this.co2 = co2;
    }

    public Condition() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getHum() {
        return hum;
    }

    public void setHum(Integer hum) {
        this.hum = hum;
    }

    public Integer getCo2() {
        return co2;
    }

    public void setCo2(Integer co2) {
        this.co2 = co2;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}