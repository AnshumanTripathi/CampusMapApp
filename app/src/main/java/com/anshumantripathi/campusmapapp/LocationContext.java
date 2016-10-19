package com.anshumantripathi.campusmapapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

/**
 * Created by AnshumanTripathi on 10/18/16.
 */

public class LocationContext extends Activity {


    public static LocationContext instance;
    private static Location point1, point2, point3, point4;

    public static void initCampusBoundaries() {

        //4th and San Fernando
        point1.setLat(37.335848);
        point1.setLng(-121.886039);

        //10th and San Fernando
        point2.setLat(37.338893);
        point2.setLng(-121.879698);

        //10th and E. San Salvador
        point3.setLat(37.334557);
        point3.setLng(-121.876453);

        //4th and E. San Salvador
        point4.setLat(37.331550);
        point4.setLng(-121.882851);

    }

    private LocationContext() {
    }

    public static LocationContext getInstance() {
        if (instance == null) {
            return new LocationContext();
        } else {
            return instance;
        }
    }

    public void displayGpsStatus() {
        LocationManager gpsStatus = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!gpsStatus.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("GPS is disabled. Enable for Location service? ")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(@SuppressWarnings("unused") DialogInterface dialog, @SuppressWarnings("unused") int which) {
                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

    public void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getBaseContext().getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

    }

//    public boolean isLocationInCampus(){
//
//    }
}
