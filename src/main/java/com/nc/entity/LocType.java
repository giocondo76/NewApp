package com.nc.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "loc_type")
public class LocType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lt_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "locType")
    private List<Location> locations;

    public LocType() {
    }

    public LocType(Integer id, String name, List<Location> locations) {
        this.id = id;
        this.name = name;
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

}