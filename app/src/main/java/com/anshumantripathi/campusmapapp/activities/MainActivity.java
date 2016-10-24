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
import android.graphics.Matrix;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.anshumantripathi.campusmapapp.model.BuildingData;
import com.anshumantripathi.campusmapapp.model.CampusData;
import com.anshumantripathi.campusmapapp.model.Coordinates;
import com.anshumantripathi.campusmapapp.util.Constants;
import com.anshumantripathi.campusmapapp.util.DistanceMatrixTask;
import com.anshumantripathi.campusmapapp.util.LocationContext;
import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.util.TravelMode;

import org.json.JSONObject;

import static com.anshumantripathi.campusmapapp.model.CampusData.initCampusBoundaries;

public class MainActivity extends AppCompatActivity {

    private static final int ACCESS_COARSE_LOCATION = 1;
    LocationContext ctx = LocationContext.getInstance();
//    Button locationBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCampusBoundaries();
        final ImageView campusImage = (ImageView) findViewById(R.id.campusImage);
        LocationContext.getInstance().resetContext();
        getCurrentLocation();
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
                        ctx.setMode(TravelMode.BICYCLING.name());

                        setDestinationLocationInContext(envX, envY);

                        //TODO: Remove this.
                        double src_lat = 37.368830;
                        double src_lng = -122.036350;
                        double des_lat = 37.338208;
                        double des_lng = -121.886329;

                        String stringURL = prepareDistanceMatrixURL(src_lat, src_lng, des_lat, des_lng, "bicycling");
                        try {
                            new DistanceMatrixTask(getApplicationContext()).execute(stringURL).get();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
                return true;
            }
        });

//        locationBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

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

    }

    public void getCurrentLocation() {
        displayGpsStatus();
        LocationManager locationManager = (LocationManager) MainActivity.this.getSystemService(LOCATION_SERVICE);
        try {
            final Coordinates currentLocation = new Coordinates();
            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
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
            };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0, locationListener);
            currentLocation.setLat(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude());
            currentLocation.setLng(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude());
            ctx.setCurrentLocation(currentLocation);
            System.out.println(ctx.getCurrentLocation().toString());
            Toast.makeText(MainActivity.this,ctx.getCurrentLocation().toString(),Toast.LENGTH_SHORT).show();
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
                        ctx.getCurrentLocation().setLat(gpsStatus.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude());
                        ctx.getCurrentLocation().setLng(gpsStatus.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude());
                    } catch (SecurityException permissionException) {
                        Toast.makeText(getBaseContext(), "Exception in Fetching last known location", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private void setDestinationLocationInContext(int envX, int envY) {
        int color = getHotspotColor(R.id.imageOverlay, envX, envY);
        int selectedColor=-1;
        Log.v("Color clicked:", Integer.toString(color));

        if(closeMatch(Color.WHITE,color,Constants.TOLERANCE)){
            Toast.makeText(MainActivity.this,"Empty",Toast.LENGTH_SHORT).show();
        }else
        if (closeMatch(Color.YELLOW, color, Constants.TOLERANCE)) {
            Toast.makeText(MainActivity.this, "Library", Toast.LENGTH_SHORT).show();
            LocationContext.getInstance().setColor(0);
            selectedColor = 0;

        } else if (closeMatch(Color.RED, color, Constants.TOLERANCE)) {
            Toast.makeText(MainActivity.this, "Engineering Building", Toast.LENGTH_SHORT).show();
            LocationContext.getInstance().setColor(1);
            selectedColor = 1;

        } else if (closeMatch(Color.BLUE, color, Constants.TOLERANCE)) {
            Toast.makeText(MainActivity.this, "YC Hall", Toast.LENGTH_SHORT).show();
            LocationContext.getInstance().setColor(2);
            selectedColor = 2;

        } else if (closeMatch(Color.GREEN, color, Constants.TOLERANCE)) {
            Toast.makeText(MainActivity.this, "Student Union", Toast.LENGTH_SHORT).show();
            LocationContext.getInstance().setColor(3);
            selectedColor = 3;

        } else if (closeMatch(Color.BLACK, color, Constants.TOLERANCE)) {
            Toast.makeText(MainActivity.this, "BBC", Toast.LENGTH_SHORT).show();
            LocationContext.getInstance().setColor(4);
            selectedColor = 4;

        } else if (closeMatch(Color.CYAN, color, Constants.TOLERANCE)) {
            Toast.makeText(MainActivity.this, "South Parking Garage", Toast.LENGTH_SHORT).show();
            LocationContext.getInstance().setColor(5);
            selectedColor = 5;

        }
        if(selectedColor == -1) {
        }
        else
            setDestinationBuildingDetails(selectedColor);
    }

    /*Based on the color clicked, it fills in the building details in the context*/
    private void setDestinationBuildingDetails(int color) {

        Log.v("Color:", Integer.toString(color));
        CampusData cd = new CampusData();
        BuildingData buil = cd.getBuildingData().get(color);

        Coordinates destC = new Coordinates();
        destC.setLat(buil.getLat());
        destC.setLng(buil.getLng());

        ctx.setDestinationLocation(destC);
        ctx.setBuildData(buil);
    }

    /*This method will prepare the Google API UrL to obtain the distance and time between two points.*/
    private String prepareDistanceMatrixURL(double src_lat, double src_long, double des_lat, double des_long, String mode) {
        String stringURL = "";
        try {
            stringURL += "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" +
                    Double.toString(src_lat) +
                    "," +
                    Double.toString(src_long) +
                    "&destinations=" +
                    Double.toString(des_lat) +
                    "," +
                    Double.toString(des_long) +
                    "&mode=" +
                    mode;
            return stringURL;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
