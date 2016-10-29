package com.anshumantripathi.campusmapapp.activities.Handlers;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.model.BuildingData;
import com.anshumantripathi.campusmapapp.model.CampusData;
import com.anshumantripathi.campusmapapp.model.Constants;
import com.anshumantripathi.campusmapapp.util.LocationContext;
import java.util.ArrayList;

public class SearchButtonClickHandler implements View.OnClickListener{
    private AppCompatActivity activity;
    private CampusData cd;
    LocationContext ctx;

    public SearchButtonClickHandler(
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
        //dismiss soft keypad
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

        //get the references to the UI elements
        EditText searchBar = (EditText) activity.findViewById(R.id.searchbar);
        String searchQuery = searchBar.getText().toString();

        //passing the search query to the search function
        searchBuilding(searchQuery);
    }

    //search for the buildings as per the search query passed.
    public void searchBuilding(String searchQuery) {
        ArrayList<BuildingData> op = new ArrayList<>();
        for (int id = 0; id < Constants.BUILD_COUNT; id++) {
            //get the building data object
            BuildingData bd = cd.getBuildingData().get(id);
            if (bd != null) {

                //obtain the name and abbr
                String buildingName = bd.getName().toLowerCase();
                String buildingAbbr = cd.getBuildingData().get(id).getAbbr().toLowerCase();

                //if the search qquery matches some text in the name or is equal to abbr
                if ((buildingName.contains(searchQuery) == true) || (buildingName.equals(buildingAbbr))) {
                    op.add(bd);
                }
            }
        }
        ctx.setSearchResult(op);
    }
}
