package com.anshumantripathi.campusmapapp.activities.Handlers;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.anshumantripathi.campusmapapp.activities.MainActivity;
import com.anshumantripathi.campusmapapp.model.CampusData;
import com.anshumantripathi.campusmapapp.util.LocationContext;

public class SearchResultsClearHandler implements View.OnClickListener{
    private AppCompatActivity activity;
    private CampusData cd;
    LocationContext ctx;

    public SearchResultsClearHandler(
            AppCompatActivity activity,
            CampusData cd,
            LocationContext ctx
    ) {
        this.activity = activity;
        this.cd = cd;
        this.ctx = ctx;
    }

    @Override
    public void onClick(View v) {
        ((MainActivity) activity).onSearchClear();
    }
}
