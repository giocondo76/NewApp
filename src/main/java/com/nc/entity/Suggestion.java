package com.nc.entity;

import javax.persistence.*;

@Entity
@Table(name = "suggestions")
public class Suggestion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sug_id")
    private Integer id;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;

    public Suggestion() {
    }

    public Suggestion(Integer id, String text, Location location) {
        this.id = id;
        this.text = text;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
