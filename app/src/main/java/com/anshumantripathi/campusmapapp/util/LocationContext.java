package com.anshumantripathi.campusmapapp.util;

import android.app.Activity;
import android.graphics.Bitmap;

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
    private static String distanceMatrixResp;
    private static Bitmap streetViewImg;

    public static Bitmap getStreetViewImg() {
        return streetViewImg;
    }

    public static void setStreetViewImg(Bitmap streetViewImg) {
        LocationContext.streetViewImg = streetViewImg;
    }

    public static String getDistanceMatrixResp() {
        return distanceMatrixResp;
    }

    public static void setDistanceMatrixResp(String distanceMatrixResp) {
        LocationContext.distanceMatrixResp = distanceMatrixResp;
    }

    public  BuildingData getBuildData() {
        return buildData;
    }

    public  void setBuildData(BuildingData newBuildData) {
        buildData = newBuildData;
    }


    public int getColor() {
        return color;
    }

    public void setColor(int newColor) {
        color = newColor;
    }

    public void setCurrentLocation(Coordinates newCurrentLocation) {
        currentLocation = newCurrentLocation;
    }

    public Coordinates getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(Coordinates userDestinationLocation) {
        destinationLocation = userDestinationLocation;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String userMode) {
        mode = userMode;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String userDistance) {
        distance = userDistance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String userTime) {
        time = userTime;
    }

    public static LocationContext getInstance() {
        if (instance == null) {
            return new LocationContext();
        } else {
            return instance;
        }
    }

    public void resetContext() {
        currentLocation = null;
        destinationLocation = null;
        mode = null;
        distance = null;
        time = null;
        buildData = new BuildingData();
    }

    public Coordinates getCurrentLocation() {
        return currentLocation;
    }

}