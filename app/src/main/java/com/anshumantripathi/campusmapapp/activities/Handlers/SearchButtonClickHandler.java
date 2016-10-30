package com.anshumantripathi.campusmapapp.activities.Handlers;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import com.anshumantripathi.campusmapapp.activities.MainActivity;
import com.anshumantripathi.campusmapapp.model.BuildingData;
import com.anshumantripathi.campusmapapp.model.Constants;
import com.anshumantripathi.campusmapapp.util.LocationContext;

import java.util.ArrayList;

public class SearchButtonClickHandler implements android.widget.SearchView.OnQueryTextListener{
    private AppCompatActivity activity;
    private LocationContext ctx;

    public SearchButtonClickHandler(
            AppCompatActivity activity,
            LocationContext ctx
    ) {
        this.activity = activity;
        this.ctx = ctx;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
       hideSoftKeypad();
        searchBuilding(query);
        ((MainActivity) activity).onBuildingDetailsFetch();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.equals("")) {
            ((MainActivity) activity).onSearchClear();
        }
        return false;
    }
    //search for the buildings as per the search query passed.
    public void searchBuilding(String searchQuery) {
        ArrayList<BuildingData> op = new ArrayList<>();
        for (int id = 0; id < Constants.BUILD_COUNT; id++) {
            //get the building data object
            BuildingData bd = ctx.cd.getBuildingData().get(id);
            if (bd != null) {

                //obtain the name and abbr
                String buildingName = bd.getName().toLowerCase();
                String buildingAbbr = ctx.cd.getBuildingData().get(id).getAbbr().toLowerCase();

                //if the search qquery matches some text in the name or is equal to abbr
                if ((buildingName.contains(searchQuery))
                        || (buildingAbbr.equals(searchQuery))) {
                    op.add(bd);
                }
            }
        }
        ctx.setSearchResult(op);
    }

    //dismiss soft keypad
    public void hideSoftKeypad(){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}
