package com.anshumantripathi.campusmapapp.model;

/**
 * Created by AnshumanTripathi on 10/19/16.
 */

public class Coordinates {

    private double lat;
    private double lng;

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

<<<<<<< HEAD

=======
>>>>>>> 990628c42d15ee38dd480c408e70131b93c01c6f
    @Override
    public String toString() {
        return "(" + lat + "," + lng + ")";
    }

}

