package com.nc.controller.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LocationDto {

    private Integer id;

    @Size(max = 255)
    @NotNull
    private String name;

    @NotNull
    private Integer typeId;


    @NotNull
    private Integer standartId;


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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getStandartId() {
        return standartId;
    }

    public void setStandartId(Integer standartId) {
        this.standartId = standartId;
    }

}
