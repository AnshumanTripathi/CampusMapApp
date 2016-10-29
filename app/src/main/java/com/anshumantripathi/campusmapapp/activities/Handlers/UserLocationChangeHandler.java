package com.anshumantripathi.campusmapapp.activities.Handlers;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.anshumantripathi.campusmapapp.util.LocationContext;

// use dependency injection as much as possible
public class UserLocationChangeHandler implements LocationListener {
    LocationContext currContext;

    public UserLocationChangeHandler(LocationContext ctx) {
        this.currContext = ctx;
    }

    @Override
    public void onLocationChanged(Location location) {
//                    try {
//                        System.out.print("CurrentLocation: " + location.toString());
//                        currentLocation.setLat(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude());
//                        currentLocation.setLng(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude());
//                        ctx.setCurrentLocation(currentLocation);
//                    }catch (SecurityException e){
//                        System.out.println("Security Exception Occured!");
//                    }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
