package com.anshumantripathi.campusmapapp.activities;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.activities.Handlers.BuildingClickHandler;
import com.anshumantripathi.campusmapapp.activities.Handlers.GenericToastManager;
import com.anshumantripathi.campusmapapp.activities.Handlers.SearchButtonClickHandler;
import com.anshumantripathi.campusmapapp.activities.Handlers.UserLocationClickHandler;
import com.anshumantripathi.campusmapapp.util.LocationContext;
import com.anshumantripathi.campusmapapp.util.PinDrawUtils;
import com.anshumantripathi.campusmapapp.util.ScreenContext;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int ACCESS_COARSE_LOCATION = 1;
    LocationContext ctx = LocationContext.getInstance();
    SearchView searchBar;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init model data
        LocationContext currAppContext = LocationContext.getInstance();
        currAppContext.resetContext();

        // ************ initialize the UI elements on screen ********************
        searchBar = (SearchView) findViewById(R.id.searchbar);
        fab = (FloatingActionButton) findViewById(R.id.location_fab);
        final ImageView campusImage = (ImageView) findViewById(R.id.campusImage);

        searchBar.setOnQueryTextListener(new SearchButtonClickHandler(this, ctx));

        campusImage.setOnTouchListener(new BuildingClickHandler(this, currAppContext));
        UserLocationClickHandler uhc = new UserLocationClickHandler(this, ctx);
        fab.setOnClickListener(uhc);

        // show user initial location
        uhc.displayCurrentUserLocation(false);
    }

    public void onBuildingDetailsFetch() {
        if (ctx.getSearchResult() == null || ctx.getSearchResult().size() == 0) {
            GenericToastManager.showGenericMsg(this, "No results found");
        } else {
            PinDrawUtils.drawPinAtPixel(this, (FrameLayout) findViewById(R.id.frameLayout));
        }
    }

    public void onSearchClear() {
        // shift pins from one array to clear pins array
        ScreenContext.pinsToClear = ScreenContext.pins;
        ScreenContext.pins = new ArrayList<>();
        PinDrawUtils.clearPinAtPixel(this, (FrameLayout) findViewById(R.id.frameLayout));
    }

    public void checkGPSPermission() {
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
                                    public void onClick(@SuppressWarnings("unused")
                                                                DialogInterface dialog,
                                                        @SuppressWarnings("unused") int which) {
                                        startActivity(
                                                new Intent(
                                                        Settings.ACTION_LOCATION_SOURCE_SETTINGS
                                                )
                                        );
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
}