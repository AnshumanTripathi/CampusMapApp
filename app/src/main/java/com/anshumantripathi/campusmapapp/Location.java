package com.anshumantripathi.campusmapapp;

/**
 * Created by AnshumanTripathi on 10/18/16.
 */

public class Location {
    private double lat;
    private double lng;
    private String address;

    public Location(){

    }
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
