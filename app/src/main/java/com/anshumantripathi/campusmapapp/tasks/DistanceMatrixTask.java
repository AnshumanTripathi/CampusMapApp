package com.anshumantripathi.campusmapapp.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.anshumantripathi.campusmapapp.activities.BuildingDetailActivity;
import com.anshumantripathi.campusmapapp.util.LocationContext;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DistanceMatrixTask extends AsyncTask<String, Void, String> {
    String server_response;

    Context context;
    public DistanceMatrixTask(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    protected String doInBackground(String... strings) {

        URL url;
        HttpURLConnection urlConnection;

        try {
            String stringURL = strings[0];
            url = new URL(stringURL);
            urlConnection = (HttpURLConnection) url.openConnection();

            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                server_response = readStream(urlConnection.getInputStream());
                Log.v("CatalogClient", server_response);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        LocationContext.getInstance().setDistanceMatrixResp(server_response);
        parseDistanceMatrix(LocationContext.getInstance().getDistanceMatrixResp());

        Intent in = new Intent(context, BuildingDetailActivity.class);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(in);
    }


    /*This method will parse the response received from the Google API - DistanceMatrix
    * It also finds out the distance and time from the response and set it in Context*/
    private void parseDistanceMatrix(String httpResponse) {
        try {
            if(httpResponse == null)
                return;

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

            Log.v("Distance:", distance);
            Log.v("Destination address:", destination_addr);
            Log.v("Time:", time);

            //set these values in context
            LocationContext.getInstance().setDistance(distance);
            LocationContext.getInstance().setTime(time);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

}
