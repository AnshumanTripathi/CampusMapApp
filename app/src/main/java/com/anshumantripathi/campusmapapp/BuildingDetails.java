package com.anshumantripathi.campusmapapp;

/**
 * Created by Somya on 10/18/2016.
 */
public class BuildingDetails {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String name;
    private String address;
    private int walkDistance;
    private int driveDistance;
    private long latitude;
    private long longitude;
}
