package com.anshumantripathi.campusmapapp.model;

/**
 * Created by AnshumanTripathi on 10/18/16.
 */

// store the building details here only. This building detail is the one which user has clicked on.
public class BuildingData {
    private String name;
    private int bimage;
    private double lat;
    private double lng;
    private String address;
    private int xPixel;
    private int yPixel;
    private Coordinates streetViewCoord;
    private String abbr;

    public String getAbbr(){
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public Coordinates getStreetViewCoord() {
        return streetViewCoord;
    }

    public void setStreetViewCoord(Coordinates streetViewCoord) {
        this.streetViewCoord = streetViewCoord;
    }

    public BuildingData(){

    }

    public Coordinates getBuildingActualCoordinates() {
        return new Coordinates(this.lat, this.lng);
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
