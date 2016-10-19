package com.anshumantripathi.campusmapapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BuildingDetail extends Activity {
    Button streetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_detail);

        streetView = (Button) findViewById(R.id.streetview);
        streetView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String stringURL = prepareDistanceMatrixURL("37.368830","122.036350","37.338208","-121.886329","bicycling");
                    new GoogleAPI().execute(stringURL).toString();
                    Log.v("Button:", GoogleAPI.server_response);
                    parseDistanceMatrix(GoogleAPI.server_response);
                } catch (Exception e) {

                }

            }
        });
    }

    private void parseDistanceMatrix(String httpResponse) {
        try {
            JSONObject jsonRespRouteDistance = new JSONObject(httpResponse)
                    .getJSONArray("rows")
                    .getJSONObject(0)
                    .getJSONArray("elements")
                    .getJSONObject(0)
                    .getJSONObject("distance");
            String distance = jsonRespRouteDistance.get("text").toString();

            JSONObject jsonRespRouteTime = new JSONObject(httpResponse)
                    .getJSONArray("rows")
                    .getJSONObject(0)
                    .getJSONArray("elements")
                    .getJSONObject(0)
                    .getJSONObject("duration");
            String time = jsonRespRouteTime.get("text").toString();

            String destination_addr = new JSONObject(httpResponse)
                    .get("destination_addresses")
                    .toString();

            Log.v("Distance:",distance);
            Log.v("Destination address:",destination_addr);
            Log.v("Time:",time);

        } catch (Exception ex) {
            Log.v("Ex:Parsing response",ex.toString());
        }

    }

    private String prepareDistanceMatrixURL(String src_lat, String src_long, String des_lat, String des_long, String mode) {
        String stringURL = "";
        try {
            stringURL += "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" +
                    src_lat +
                    "," +
                    src_long +
                    "&destinations=" +
                    des_lat +
                    "," +
                    des_long +
                    "&mode=" +
                    mode;
            return stringURL;
        } catch(Exception ex) {
            Log.v("Prepare URL exception",ex.toString());
        }
        return null;
    }

}