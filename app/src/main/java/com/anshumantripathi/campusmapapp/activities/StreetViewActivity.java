package com.anshumantripathi.campusmapapp.activities;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.util.DistanceMatrixTask;
import com.anshumantripathi.campusmapapp.util.LocationContext;

public class StreetViewActivity extends AppCompatActivity {

    ImageView streetview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_view);

        streetview = (ImageView)findViewById(R.id.streetview);
        streetview.setImageBitmap(LocationContext.getInstance().getStreetViewImg());
    }
}
