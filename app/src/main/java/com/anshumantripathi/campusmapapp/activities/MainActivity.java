package com.anshumantripathi.campusmapapp.activities;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.model.BuildingData;
import com.anshumantripathi.campusmapapp.model.Coordinates;
import com.anshumantripathi.campusmapapp.util.LocationContext;

import static com.anshumantripathi.campusmapapp.model.CampusData.buildingData;
import static com.anshumantripathi.campusmapapp.model.CampusData.initCampusBoundaries;

public class MainActivity extends AppCompatActivity {

    private static final int ACCESS_COARSE_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCampusBoundaries();

        final ImageView campusImage = (ImageView) findViewById(R.id.campusImage);
        LocationContext.resetContext();

        campusImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int action = event.getAction();
                int envX = (int) event.getX();
                int envY = (int) event.getY();
                ImageView imageView = (ImageView) v.findViewById(R.id.campusImage);
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                        //Assuming you use a RelativeLayout
                        ImageView iv = new ImageView(getApplicationContext());
                        lp.setMargins(envX, envY, 0, 0);
                        iv.setLayoutParams(lp);
//                        iv.setImageDrawable(getResources().getDrawable(R.drawable.pin));
                        Canvas canvas = new Canvas();
                        Bitmap pin = BitmapFactory.decodeResource(getResources(), R.drawable.pin);
                        canvas.drawBitmap(pin, envX, envY, null);
                        v.draw(canvas);
                        break;
                    case MotionEvent.ACTION_UP:
                        
                        setDestinationLocationInContext(envX, envY);


                        imageView.setImageResource(R.drawable.campus_image);
                        imageView.setTag(R.drawable.campus_image);

                        Intent in = new Intent(getApplicationContext(), BuildingDetailActivity.class);
                        startActivity(in);

                        break;
                }
                return true;
            }
        });

//        Button locationButton = (Button) findViewById(R.id.location);
    }


    public int getHotspotColor(int hotspotId, int x, int y) {
        ImageView img = (ImageView) findViewById(hotspotId);
        img.setDrawingCacheEnabled(true);
        Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
        img.setDrawingCacheEnabled(false);
        return hotspots.getPixel(x, y);
    }

    public boolean closeMatch(int color1, int color2, int tolerance) {
        if (Math.abs(Color.red(color1) - Color.red(color2)) > tolerance)
            return false;
        if (Math.abs(Color.green(color1) - Color.green(color2)) > tolerance)
            return false;
        if (Math.abs(Color.blue(color1) - Color.blue(color2)) > tolerance)
            return false;
        return true;
    }

    public void displayGpsStatus() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, ACCESS_COARSE_LOCATION);
        }
        getCurrentLocation();


    }

    public void getCurrentLocation() {
        displayGpsStatus();
        LocationManager locationManager = (LocationManager) getBaseContext().getSystemService(LOCATION_SERVICE);

        try {
            LocationContext.getCurrentLocation().setLat(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude());
            LocationContext.getCurrentLocation().setLng(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude());
        } catch (SecurityException permissionException) {
            Toast.makeText(getBaseContext(), "Location permission might be missing. Check GPS", Toast.LENGTH_SHORT).show();
            displayGpsStatus();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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
                        LocationContext.getCurrentLocation().setLat(gpsStatus.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude());
                        LocationContext.getCurrentLocation().setLng(gpsStatus.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude());
                    } catch (SecurityException permissionException) {
                        Toast.makeText(getBaseContext(), "Exception in Fetching last known location", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private  void setDestinationLocationInContext(int envX, int envY) {
        int color = getHotspotColor(R.id.imageOverlay, envX, envY);
        int selectedColor = 0;

        if (closeMatch(Color.RED, color, 40)) {

            Toast.makeText(MainActivity.this, "Engineering Building", Toast.LENGTH_SHORT).show();
            LocationContext.setColor(1);
            selectedColor = 1;


        }

        else if (closeMatch(Color.GREEN, color, 40)) {

            Toast.makeText(MainActivity.this, "Student Union Building", Toast.LENGTH_SHORT).show();
        }

        else if (closeMatch(Color.YELLOW, color, 40)) {

            Toast.makeText(MainActivity.this, "Library Building", Toast.LENGTH_SHORT).show();
        }

        setDestinationBuildingDetails(selectedColor);
    }
    

    private void setDestinationBuildingDetails(int color) {
        BuildingData buil = buildingData.get(color);

        Coordinates destC = new Coordinates();
        destC.setLat(buil.getLat());
        destC.setLng(buil.getLng());

        LocationContext.setDestinationLocation(destC);
        LocationContext.setBuildData(buil);
    }
}

