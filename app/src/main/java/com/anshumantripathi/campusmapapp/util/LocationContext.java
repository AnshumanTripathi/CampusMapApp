package com.anshumantripathi.campusmapapp.util;

import android.app.Activity;
import android.graphics.Bitmap;

import com.anshumantripathi.campusmapapp.model.BuildingData;
import com.anshumantripathi.campusmapapp.model.CampusData;
import com.anshumantripathi.campusmapapp.model.Coordinates;

import java.util.ArrayList;

// This class contains the context of the current app.
public class LocationContext extends Activity {

    private static LocationContext instance;
    public CampusData cd = new CampusData();

    public static void setInstance(LocationContext instance) {
        LocationContext.instance = instance;
    }

    //************** data about search operation **************
    static ArrayList<BuildingData> searchResult = new ArrayList<>();

    //********** data about building and transit detail (when someone clicks on a building) *******
    private static String distance;
    private static BuildingData buildData;
    private static String mode;
    private static String time;

    //********** detail about the panaromic view *********************
    private static Bitmap streetViewImg;
    private static long xPixel;
    private static long yPixel;

    //********** detail about the current user *****************
    private static Coordinates currentLocation = null;
    //this is a redundant param, we can plan to remove it.
    private static Coordinates destinationLocation;

    private static int color;
    private static String distanceMatrixResp;

    private LocationContext() {
    }

    public long getxPixel() {
        return xPixel;
    }

    public void setxPixel(long newxPixel) {
        xPixel = newxPixel;
    }

    public long getyPixel() {
        return yPixel;
    }

    public void setyPixel(long newyPixel) {
        yPixel = newyPixel;
    }


    public Bitmap getStreetViewImg() {
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
        destinationLocation = null;
        currentLocation = null;
        color = 0;
        distanceMatrixResp = "";
    }

    public Coordinates getCurrentLocation() {
        return currentLocation;
    }

    public ArrayList<BuildingData> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(ArrayList<BuildingData> searchResult) {
        this.searchResult = searchResult;
    }
}