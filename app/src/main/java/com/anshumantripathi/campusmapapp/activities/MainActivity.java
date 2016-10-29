package com.anshumantripathi.campusmapapp.activities;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.activities.Handlers.BuildingClickHandler;
import com.anshumantripathi.campusmapapp.activities.Handlers.GenericToastManager;
import com.anshumantripathi.campusmapapp.activities.Handlers.SearchButtonClickHandler;
import com.anshumantripathi.campusmapapp.activities.Handlers.UserLocationChangeHandler;
import com.anshumantripathi.campusmapapp.model.CampusData;
import com.anshumantripathi.campusmapapp.model.Coordinates;
import com.anshumantripathi.campusmapapp.model.RedDot;
import com.anshumantripathi.campusmapapp.util.LocationContext;

public class MainActivity extends AppCompatActivity {

    private static final int ACCESS_COARSE_LOCATION = 1;
    LocationContext ctx = LocationContext.getInstance();
    EditText searchbar;
    ImageButton searchbutton;
    ImageButton clear;
    FloatingActionButton fab;
    CampusData cd = new CampusData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // init model data
        LocationContext currAppContext = LocationContext.getInstance();
        currAppContext.resetContext();

        // 1. campus details
        cd.initCampusBoundaries();

        // 2. current user details
        getCurrentLocation();

        // ************ initialize the UI elements on screen ********************
        searchbar = (EditText) findViewById(R.id.searchbar);
        searchbutton = (ImageButton) findViewById(R.id.search_button);
        clear = (ImageButton) findViewById(R.id.clear);
        fab = (FloatingActionButton) findViewById(R.id.location_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        final ImageView campusImage = (ImageView) findViewById(R.id.campusImage);

        // ******************** Test code **************************

        double lat = 37.335142;
        double lng = -121.881276;
        lat = lat * Math.PI / 180;
        lng = lng * Math.PI / 180;

        DisplayMetrics metrics = getResources().getDisplayMetrics();

        long screenWidth = metrics.widthPixels;
        long screenHeight = metrics.heightPixels;

//        long screenHeight = 850;//campusImage.getMaxHeight();
//        long screenWidth = 740; //campusImage.getMaxWidth();

        Log.v("Width: ", String.valueOf(screenWidth));
        Log.v("Height: ", String.valueOf(screenHeight));

//        int y =  (int) Math.round(((-1 * lat) + 90) * (screenHeight / 180));
//        int x =  (int) Math.round((lng + 180) * (screenWidth / 360));

//        Log.v("x",String.valueOf(x));
//        Log.v("y",String.valueOf(y));


        ctx.setxPixel(screenWidth / 2);
        ctx.setyPixel(screenHeight / 2);
        RedDot locatoinDot = new RedDot(MainActivity.this);
        FrameLayout fLayout = (FrameLayout) findViewById(R.id.frameLayout);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        fLayout.addView(locatoinDot, params);
        // *********************************************************

        campusImage.setOnTouchListener(new BuildingClickHandler(this, currAppContext));

        searchbutton.setOnClickListener(new SearchButtonClickHandler(this,cd, ctx));
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchbar.getText().clear();
            }
        });
    }

    public void onBuildingDetailsFetch() {

    }

    public void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_COARSE_LOCATION
            );
        }

    }

    public void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean isGpsOn = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkOn = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//        boolean isProvider = locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER);

        try {
            final Coordinates currentLocation = new Coordinates();
            if (isGpsOn) {
                // great. We can now show the user Dot.
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        0,
                        0,
                        new UserLocationChangeHandler(LocationContext.getInstance())
                );

                Location userLastLocation = locationManager.getLastKnownLocation(
                        LocationManager.GPS_PROVIDER);

                if (userLastLocation != null) {
                    currentLocation.setLat(userLastLocation.getLatitude());
                    currentLocation.setLng(userLastLocation.getLongitude());
                    ctx.setCurrentLocation(currentLocation);
                    GenericToastManager.showGenericMsg(
                            getBaseContext(),
                            currentLocation.toString()
                    );
                } else {
                    GenericToastManager.showGenericMsg(
                            getBaseContext(),
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
                            getBaseContext(),
                            currentLocation.toString()
                    );
                } else {
                    GenericToastManager.showGenericMsg(
                            getBaseContext(),
                            "Failed to get user location."
                    );
                }
            } else {
                GenericToastManager.showGenericMsg(
                        getBaseContext(),
                        "Cannot get Location! Check Network or GPS."
                );
            }
        } catch (Exception e) {
            System.out.println("Exception while getting user location: " + e.getMessage());
            GenericToastManager.showGenericMsg(
                    getBaseContext(),
                    "Location permission might be missing. Check GPS."
            );
            checkPermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults
    ) {
        LocationManager gpsStatus = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        switch (requestCode) {
            case ACCESS_COARSE_LOCATION:
                if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (!gpsStatus.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
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

                        android.app.AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "App requries Location to perform all Features", Toast.LENGTH_SHORT).show();
                    try {
                        ctx.getCurrentLocation().setLat(gpsStatus.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude());
                        ctx.getCurrentLocation().setLng(gpsStatus.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude());
                    } catch (SecurityException permissionException) {
                        Toast.makeText(getBaseContext(), "Exception in Fetching last known location", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }


    public EditText getSearchbar(){
        return this.searchbar;
    }

}