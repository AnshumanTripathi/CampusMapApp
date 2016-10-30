package com.anshumantripathi.campusmapapp.util;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.anshumantripathi.campusmapapp.activities.Handlers.GenericToastManager;
import com.anshumantripathi.campusmapapp.activities.MainActivity;
import com.anshumantripathi.campusmapapp.model.Coordinates;

public class UserLocationFinderHelper {
    // test code
    private static int testLocation = 0;
    static Location userLastLocation;

    public static void updateCurrentLocation(
            AppCompatActivity appActivity,
            LocationContext ctx,
            boolean explicitUpdate
    ) {

//        if (testLocation >= 5) {
//            testLocation =0;
//        } else {
//            testLocation++;
//        }
//        ctx.setCurrentLocation(
//                ctx.cd.getBuildingData().get(testLocation).getBuildingActualCoordinates());
//        ctx.setCurrentLocation(new Coordinates(37.332687, -121.880515));
//        return;

        LocationManager locationManager = (LocationManager) appActivity.getSystemService(
                Context.LOCATION_SERVICE);
        boolean isGpsOn = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkOn = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        try {

            final Coordinates currentLocation = new Coordinates();
            LocationListener listener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    userLastLocation = location;
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            };
            if (isGpsOn) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
                userLastLocation = locationManager.getLastKnownLocation(
                        LocationManager.GPS_PROVIDER);
                if (userLastLocation != null) {
                    currentLocation.setLat(userLastLocation.getLatitude());
                    currentLocation.setLng(userLastLocation.getLongitude());
                    ctx.setCurrentLocation(currentLocation);
                } else {
                    GenericToastManager.showGenericMsg(
                            appActivity.getBaseContext(),
                            "Failed to get user location."
                    );
                }
            } else if (isNetworkOn) {
                String bestProvider = locationManager.getBestProvider(new Criteria(), false);
                Location location = locationManager.getLastKnownLocation(bestProvider);
                if (location != null) {
                    currentLocation.setLat(location.getLatitude());
                    currentLocation.setLng(location.getLongitude());
                    ctx.setCurrentLocation(currentLocation);
                    GenericToastManager.showGenericMsg(
                            appActivity.getBaseContext(),
                            currentLocation.toString()
                    );
                } else {
                    GenericToastManager.showGenericMsg(
                            appActivity.getBaseContext(),
                            "Failed to get user location."
                    );
                }
            } else {
                GenericToastManager.showGenericMsg(
                        appActivity.getBaseContext(),
                        "Cannot get Location! Check Network or GPS."
                );
            }
        } catch (Exception e) {
            System.out.println("Exception while getting user location: " + e.getMessage());
            GenericToastManager.showGenericMsg(
                    appActivity.getBaseContext(),
                    "Location permission might be missing. Check GPS."
            );
            ((MainActivity)appActivity).checkGPSPermission();
        }
    }

}
