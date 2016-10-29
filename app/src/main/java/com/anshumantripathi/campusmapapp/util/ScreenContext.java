package com.anshumantripathi.campusmapapp.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.activities.MainActivity;
import com.anshumantripathi.campusmapapp.model.CampusData;
import com.anshumantripathi.campusmapapp.model.Pin;

/**
 * Created by AnshumanTripathi on 10/26/16.
 */

public class ScreenContext extends Activity{
    private static ScreenContext instance;
    private int xPixel;
    private int yPixel;
    private float pixelPerLocation;
    private int screenWidth;
    private int screenHeight;

    public static ScreenContext getInstance(){
        if(instance == null)
            return new ScreenContext();
        else return instance;
    }

    public int getxPixel() {
        return xPixel;
    }

    public void setxPixel(int xPixel) {
        this.xPixel = xPixel;
    }

    public int getyPixel() {
        return yPixel;
    }

    public void setyPixel(int yPixel) {
        this.yPixel = yPixel;
    }

    public void setPixelPerLocation(Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        screenHeight = metrics.heightPixels;
        screenWidth = metrics.widthPixels;
        CampusData campusData = new CampusData();
        campusData.initCampusBoundaries();
        campusData.getPoint1().getLat();
    }

    public void drawPin(Context context){
        Pin pin = new Pin(context, this.xPixel, this.yPixel);
        FrameLayout fLayout = (FrameLayout) findViewById(R.id.frameLayout);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        fLayout.addView(pin,params);

    }
}