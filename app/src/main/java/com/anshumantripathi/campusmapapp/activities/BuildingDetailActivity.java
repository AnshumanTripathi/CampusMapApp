package com.anshumantripathi.campusmapapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.util.LocationContext;

public class BuildingDetailActivity extends AppCompatActivity {
    FloatingActionButton streetView;
    TextView baddress;
    TextView distance;
    TextView time;
    ImageView bimage;
    LocationContext ctx = LocationContext.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_detail);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));
        myToolbar.setNavigationIcon(R.drawable.back);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuildingDetailActivity.this, MainActivity.class));
            }
        });

        streetView = (FloatingActionButton) findViewById(R.id.streetview);
        baddress = (TextView) findViewById(R.id.baddress);
        distance = (TextView) findViewById(R.id.distance);
        time = (TextView) findViewById(R.id.time);
        bimage = (ImageView) findViewById(R.id.bimage);

        //Display all the params on the screen
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

}