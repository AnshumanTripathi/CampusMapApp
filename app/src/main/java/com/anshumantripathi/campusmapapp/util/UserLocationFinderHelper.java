package com.anshumantripathi.campusmapapp.util;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;

import com.anshumantripathi.campusmapapp.activities.Handlers.GenericToastManager;
import com.anshumantripathi.campusmapapp.activities.MainActivity;
import com.anshumantripathi.campusmapapp.model.Coordinates;

public class UserLocationFinderHelper {
    // test code
    private static int testLocation = 0;

    public static void updateCurrentLocation(
            AppCompatActivity appActivity,
            LocationContext ctx,
            boolean explicitUpdate
    ) {

        LocationManager locationManager = (LocationManager) appActivity.getSystemService(
                Context.LOCATION_SERVICE);
        boolean isGpsOn = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkOn = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        try {
            final Coordinates currentLocation = new Coordinates();
            if (isGpsOn) {
                Location userLastLocation = locationManager.getLastKnownLocation(
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

            if (testLocation >= 5) {
                testLocation =0;
            } else {
                testLocation++;
            }
//        ctx.setCurrentLocation(
//                ctx.cd.getBuildingData().get(testLocation).getBuildingActualCoordinates());
//        ctx.setCurrentLocation(new Coordinates(37.334815, -121.878942));

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
