package com.auca.library.model;

import java.util.UUID;

public class Location {
    private final String id;
    private String province;
    private String district;
    private String sector;
    private String cell;
    private String village;

    public Location(String province, String district, String sector, String cell, String village) {
        this.id = UUID.randomUUID().toString(); // Generates a unique ID using UUID
        this.province = province;
        this.district = district;
        this.sector = sector;
        this.cell = cell;
        this.village = village;
    }

    public String getId() {
        return id;
    }

    public String getProvince() {
        return province;
    }

    public String getVillage() {
        return village;
    }
}
