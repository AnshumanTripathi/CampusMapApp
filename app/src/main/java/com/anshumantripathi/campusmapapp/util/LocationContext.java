package com.anshumantripathi.campusmapapp.util;

import android.app.Activity;

import com.anshumantripathi.campusmapapp.model.BuildingData;
import com.anshumantripathi.campusmapapp.model.Coordinates;

public class LocationContext extends Activity {


    private static LocationContext instance;

    public static void setInstance(LocationContext instance) {
        LocationContext.instance = instance;
    }

    private static Coordinates currentLocation;
    //this is a redundant param, we can plan to remove it.
    private static Coordinates destinationLocation;
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

    public static void setCurrentLocation(Coordinates newCurrentLocation) {
        currentLocation = newCurrentLocation;
    }

    public static Coordinates getDestinationLocation() {
        return destinationLocation;
    }

    public static void setDestinationLocation(Coordinates userDestinationLocation) {
        destinationLocation = userDestinationLocation;
    }

    public static String getMode() {
        return mode;
    }

    public static void setMode(String userMode) {
        mode = userMode;
    }

    public static String getDistance() {
        return distance;
    }

    public static void setDistance(String userDistance) {
        distance = userDistance;
    }

    public static String getTime() {
        return time;
    }

    public static void setTime(String userTime) {
        time = userTime;
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

    public static Coordinates getCurrentLocation() {
        return currentLocation;
    }

}
