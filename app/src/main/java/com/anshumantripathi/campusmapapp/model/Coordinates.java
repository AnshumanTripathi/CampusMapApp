package com.anshumantripathi.campusmapapp.model;

/**
 * Created by AnshumanTripathi on 10/19/16.
 */

public class Coordinates {

    private double lat; // lat is y
    private double lng; // longitude is x

    public Coordinates() {
    }

    public Coordinates(double lat, double lng){
        this.lat = lat;
        this.lng = lng;
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

    @Override
    public String toString() {
        return "(" + lat + "," + lng + ")";
    }

}

