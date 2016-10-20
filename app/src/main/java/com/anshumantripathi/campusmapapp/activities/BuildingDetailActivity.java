package com.anshumantripathi.campusmapapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anshumantripathi.campusmapapp.util.DistanceMatrixTask;
import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.util.LocationContext;

import org.json.JSONObject;

public class BuildingDetailActivity extends Activity {
    Button streetView;
    TextView bname;
    TextView baddress;
    TextView distance;
    TextView time;
    LocationContext ctx = LocationContext.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_detail);

        streetView = (Button) findViewById(R.id.streetview);
        bname = (TextView) findViewById(R.id.bname);
        baddress = (TextView) findViewById(R.id.baddress);
        distance = (TextView) findViewById(R.id.distance);
        time = (TextView) findViewById(R.id.time);

        //Display all the params on the screen
        bname.setText(ctx.getBuildData().getName());
        baddress.setText(ctx.getBuildData().getAddress());
        distance.setText(ctx.getDistance());
        time.setText(ctx.getTime());

        //display street view on clicking on the street view button
        streetView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }


}