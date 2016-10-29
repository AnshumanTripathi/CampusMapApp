package com.anshumantripathi.campusmapapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.model.Constants;
import com.anshumantripathi.campusmapapp.tasks.StreetViewTask;
import com.anshumantripathi.campusmapapp.util.LocationContext;

public class BuildingDetailActivity extends Activity {
    FloatingActionButton streetView;
    TextView bname;
    TextView baddress;
    TextView distance;
    TextView time;
    ImageView bimage;
    LocationContext ctx = LocationContext.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_detail);

        streetView = (FloatingActionButton) findViewById(R.id.streetview);
//        bname = (TextView) findViewById(R.id.bname);
        baddress = (TextView) findViewById(R.id.baddress);
        distance = (TextView) findViewById(R.id.distance);
        time = (TextView) findViewById(R.id.time);
        bimage = (ImageView) findViewById(R.id.bimage);

        //Display all the params on the screen
//        bname.setText(ctx.getBuildData().getName());
        baddress.setText(ctx.getBuildData().getAddress());
        distance.setText(ctx.getDistance());
        time.setText(ctx.getTime());
        bimage.setImageResource(ctx.getBuildData().getBimage());

        //display street view on clicking on the street view button
        streetView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), StreetViewActivity.class);
                startActivity(in);
            }
        });
    }

    //TODO Check this Method
    private String prepareStreetViewURL(int sizeX, int sizeY, String address) {
        String newAddress = address.replace(" ","%20");
        String url = "";

        url += "https://maps.googleapis.com/maps/api/streetview?";
        url += "size=" + Integer.toString(sizeX) + "x" + Integer.toString(sizeY);
        url += "&location=";
        url += newAddress;
//        if(heading != -1) {
//            url += "&heading=";
//            url += Double.toString(heading);
//        }
        Log.v("URL:",url);
        return url;
    }
}