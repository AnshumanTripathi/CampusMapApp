package com.anshumantripathi.campusmapapp.activities.Handlers;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.model.BuildingData;
import com.anshumantripathi.campusmapapp.model.CampusData;
import com.anshumantripathi.campusmapapp.model.Constants;

import java.util.ArrayList;

/**
 * Created by saurabg on 10/28/16.
 */

//public class SearchButtonClickHandler extends Activity implements View.OnClickListener{
//    @Override
//    public void onClick(View v) {
//
//        //dismiss soft keypad
//        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
//
//        EditText searchBar = (EditText) findViewById(R.id.searchbar);
//        String searchQuery = searchBar.getText().toString();
//        ArrayList<String> op = searchBuilding(searchQuery);
//        ArrayList<BuildingData> buildingData = new CampusData().getBuildingData();
//        for (String searchResult : op) {
//
//        }
//
//    }
//
//    public ArrayList<String> searchBuilding(String searchQuery) {
//        Log.v("Search Query:", searchQuery);
//        ArrayList<String> op = new ArrayList<>();
//        for (int id = 0; id < Constants.BUILD_COUNT; id++) {
//            String buildingName = cd.buildingData.get(id).getName().toLowerCase();
//            Log.v("CHecking Building:", buildingName);
//            if (buildingName.contains(searchQuery) == true) {
//                Log.v("MATCHES:", buildingName);
//                op.add(buildingName);
//            }
//        }
//        return op;
//    }
//
//}
