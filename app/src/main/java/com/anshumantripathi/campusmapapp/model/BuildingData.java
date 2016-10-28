package com.anshumantripathi.campusmapapp.model;

import android.graphics.Bitmap;

import static android.R.attr.bitmap;

/**
 * Created by AnshumanTripathi on 10/18/16.
 */

public class BuildingData {
    private String name;
    private int bimage;
    private double lat;
    private double lng;
    private String address;
    private int xPixel;
    private int yPixel;


    private double heading;
    private String streetAddress;

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public double getHeading() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public BuildingData(){

    }

    public int getBimage() {
        return bimage;
    }

    public void setBimage(int bimage) {
        this.bimage = bimage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getxPixel() {
        return xPixel;
    }

    public void setxPixel(int xPixel) {
        this.xPixel = xPixel;
    }

    public int getyPixel() {
        return yPixel;
    }

    public void setyPixel(int yPixel) {
        this.yPixel = yPixel;
    }

}
