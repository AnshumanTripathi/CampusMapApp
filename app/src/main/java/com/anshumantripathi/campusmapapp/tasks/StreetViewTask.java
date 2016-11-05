package com.anshumantripathi.campusmapapp.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.util.LocationContext;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StreetViewTask extends AsyncTask<String, Void, String> {
    Bitmap img;

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


                InputStream instream = urlConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                int len = 0;
                try {
                    while ((len = instream.read(buffer)) != -1) {
                        baos.write(buffer, 0, len);
                    }
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                byte[] b = baos.toByteArray();
                img = BitmapFactory.decodeByteArray(b, 0, b.length);
                Log.v("image----------------->",img.toString());
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
        LocationContext.setStreetViewImg(img);
    }
}
