package com.anshumantripathi.campusmapapp.activities.Handlers;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.activities.MainActivity;
import com.anshumantripathi.campusmapapp.model.BuildingData;
import com.anshumantripathi.campusmapapp.model.CampusData;
import com.anshumantripathi.campusmapapp.model.Constants;
import com.anshumantripathi.campusmapapp.model.Coordinates;
import com.anshumantripathi.campusmapapp.model.TravelMode;
import com.anshumantripathi.campusmapapp.tasks.DistanceMatrixTask;
import com.anshumantripathi.campusmapapp.util.LocationContext;
import com.anshumantripathi.campusmapapp.util.MapUtils;

import java.util.concurrent.ExecutionException;


public class BuildingClickHandler implements View.OnTouchListener {
    private LocationContext currContext;
    private AppCompatActivity appActivity;

    public BuildingClickHandler(AppCompatActivity activity, LocationContext ctx) {
        this.appActivity = activity;
        this.currContext = ctx;
    }

    public int getHotspotColor(int hotspotId, int x, int y) {
        ImageView img = (ImageView) this.appActivity.findViewById(hotspotId);
        img.setDrawingCacheEnabled(true);
        Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
        img.setDrawingCacheEnabled(false);
        System.out.println("clicked pixels are: " + x + " " + y);
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

    /*Based on the color clicked, it fills in the building details in the context*/
    private void setDestinationBuildingDetails(int color) {

        Log.v("Color:", Integer.toString(color));
        CampusData cd = new CampusData();
        BuildingData buil = cd.getBuildingData().get(color);

        Coordinates destC = new Coordinates();
        destC.setLat(buil.getLat());
        destC.setLng(buil.getLng());

        currContext.setDestinationLocation(destC);
        currContext.setBuildData(buil);
    }

    private boolean setDestinationLocationInContext(int envX, int envY) {
        int color = getHotspotColor(R.id.imageOverlay, envX, envY);
        int selectedColor = -1;
        Log.v("Color clicked:", Integer.toString(color));

        if (closeMatch(Color.WHITE, color, Constants.TOLERANCE)) {
            GenericToastManager.showGenericMsg(
                    this.appActivity.getBaseContext(),
                    "Empty Destination."
            );
        } else if (closeMatch(Color.YELLOW, color, Constants.TOLERANCE)) {
            GenericToastManager.showGenericMsg(
                    this.appActivity.getBaseContext(),
                    "Library"
            );
            currContext.setColor(0);
            selectedColor = 0;
        } else if (closeMatch(Color.RED, color, Constants.TOLERANCE)) {
            GenericToastManager.showGenericMsg(
                    this.appActivity.getBaseContext(),
                    "Engineering Building."
            );
            currContext.setColor(1);
            selectedColor = 1;

        } else if (closeMatch(Color.BLUE, color, Constants.TOLERANCE)) {
            GenericToastManager.showGenericMsg(
                    this.appActivity.getBaseContext(),
                    "YC Hall."
            );
            currContext.setColor(2);
            selectedColor = 2;
        } else if (closeMatch(Color.GREEN, color, Constants.TOLERANCE)) {
            GenericToastManager.showGenericMsg(
                    this.appActivity.getBaseContext(),
                    "Student Union."
            );
            currContext.setColor(3);
            selectedColor = 3;

        } else if (closeMatch(Color.BLACK, color, Constants.TOLERANCE)) {
            GenericToastManager.showGenericMsg(
                    this.appActivity.getBaseContext(),
                    "BBC"
            );
            currContext.setColor(4);
            selectedColor = 4;

        } else if (closeMatch(Color.CYAN, color, Constants.TOLERANCE)) {
            GenericToastManager.showGenericMsg(
                    this.appActivity.getBaseContext(),
                    "South Parking Garage."
            );
            currContext.setColor(5);
            selectedColor = 5;

        }
        if (selectedColor == -1) {
            Log.v("INVALID:", "No Valid building has been touched.");
            return false;
        } else {
            setDestinationBuildingDetails(selectedColor);
            return true;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        // check if user location is known or not at this point
        if (currContext.getCurrentLocation() == null) {
            GenericToastManager.showLocationUnavailableError(
                    this.appActivity.getBaseContext()
            );
            return false;
        }

        final int action = event.getAction();
        int envX = (int) event.getX();
        int envY = (int) event.getY();
        ImageView imageView = (ImageView) v.findViewById(R.id.campusImage);

        switch (action) {
            case MotionEvent.ACTION_UP:
                System.out.println("Pixel: "+envX+","+envY);
                this.currContext.setMode(TravelMode.BICYCLING.name());
                boolean isValidLocClicked = setDestinationLocationInContext(envX, envY);
                try {
                    if (isValidLocClicked) {
                        double src_lat = this.currContext.getCurrentLocation().getLat();
                        double src_lng = this.currContext.getCurrentLocation().getLng();
                        double des_lat = this.currContext.getDestinationLocation().getLat();
                        double des_lng = this.currContext.getDestinationLocation().getLng();

                        String stringURL = MapUtils.prepareDistanceMatrixURL(
                                src_lat,
                                src_lng,
                                des_lat,
                                des_lng,
                                "bicycling"
                        );
                        // start async task to fetch the building details
                        new DistanceMatrixTask(
                                this.appActivity.getApplicationContext()).execute(stringURL).get();
                    }
                } catch (Exception e) {
                    GenericToastManager.showGenericMsg(
                            this.appActivity.getBaseContext(),
                            "Some Error Occurred while getting Disatnce."
                    );
                    e.printStackTrace();
                }
                break;
        }
        return true;
    }

}
