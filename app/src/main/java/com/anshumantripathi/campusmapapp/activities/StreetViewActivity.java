package com.anshumantripathi.campusmapapp.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.model.Coordinates;
import com.anshumantripathi.campusmapapp.util.LocationContext;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;

public class StreetViewActivity extends AppCompatActivity {

    // Sjsu campus
    static double lat = 37.335785;
    static double lng = -121.885790;
    private static final LatLng SJSU = new LatLng(lat,lng);


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_view);

        Coordinates crd = LocationContext.getInstance().getBuildData().getStreetViewCoord();
        if(crd != null) {
            lat = crd.getLat();
            lng = crd.getLng();
        }

        SupportStreetViewPanoramaFragment streetViewPanoramaFragment =
                (SupportStreetViewPanoramaFragment)
                        getSupportFragmentManager().findFragmentById(R.id.street_view_panorama);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(
                new OnStreetViewPanoramaReadyCallback() {
                    @Override
                    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
                        // Only set the panorama to SYDNEY on startup (when no panoramas have been
                        // loaded which is when the savedInstanceState is null).
                        panorama.setPosition(new LatLng(lat,lng));
//                        if (savedInstanceState == null) {
//                            panorama.setPosition(SJSU);
//                        } else {
//                            panorama.setPosition(new LatLng(lat,lng));
//                        }
                    }
                });
    }
}
