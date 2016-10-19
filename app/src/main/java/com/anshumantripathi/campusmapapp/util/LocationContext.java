package com.anshumantripathi.campusmapapp.util;

import android.app.Activity;
import com.anshumantripathi.campusmapapp.Model.Coordiantes;
import com.anshumantripathi.campusmapapp.Model.BuildingData;

public class LocationContext extends Activity {


    public static LocationContext instance;

    public static void setInstance(LocationContext instance) {
        LocationContext.instance = instance;
    }

    private static Coordiantes currentLocation;
    //this is a redundant param, we can plan to remove it.
    private static Coordiantes destinationLocation;
    private static String mode;
    private static String distance;
    private static String time;
    private static int color;
    private static BuildingData buildData;

    public static BuildingData getBuildData() {
        return buildData;
    }

    public static void setBuildData(BuildingData buildData) {
        LocationContext.buildData = buildData;
    }


    public static int getColor() {
        return color;
    }

    public static void setColor(int color) {
        LocationContext.color = color;
    }

    public void setCurrentLocation(Coordiantes currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Coordiantes getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(Coordiantes destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static LocationContext getInstance() {
        if (instance == null) {
            return new LocationContext();
        } else {
            return instance;
        }
    }

    public static void resetContext() {
        currentLocation = null;
        destinationLocation = null;
        mode = null;
        distance = null;
        time = null;
        buildData = new BuildingData();
    }
}
