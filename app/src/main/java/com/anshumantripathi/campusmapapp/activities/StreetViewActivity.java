package com.anshumantripathi.campusmapapp.activities;

<<<<<<< HEAD
=======
import android.media.Image;
>>>>>>> 990628c42d15ee38dd480c408e70131b93c01c6f
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.anshumantripathi.campusmapapp.R;
<<<<<<< HEAD
=======
import com.anshumantripathi.campusmapapp.util.DistanceMatrixTask;
>>>>>>> 990628c42d15ee38dd480c408e70131b93c01c6f
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
