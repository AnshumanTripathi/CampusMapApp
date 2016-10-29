package com.anshumantripathi.campusmapapp.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.util.LocationContext;
import com.anshumantripathi.campusmapapp.util.ScreenContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by AnshumanTripathi on 10/24/16.
 */

public class Pin extends View {

    Bitmap pin;
    Paint mPaint;
    int xP, yP;
    LocationContext ctx;
    Logger log = Logger.getLogger(Pin.class.getName());
    Map<Integer,Integer> pixels = new HashMap<Integer, Integer>();

    public Pin(Context context) {
        super(context);
        ctx = LocationContext.getInstance();
        if(ctx.getSearchResult() != null) {
           for(BuildingData data : ctx.getSearchResult()){
               pixels.put(data.getxPixel(),data.getyPixel());
           }
        }
        setWillNotDraw(false);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inMutable = true;
        pin = BitmapFactory.decodeResource(getResources(), R.drawable.pin,opts);
        mPaint = new Paint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        log.info("onDraw Called!"+"xP: "+xP+"yP: "+yP);
        for(Map.Entry<Integer,Integer> map : pixels.entrySet()) {
            canvas.drawBitmap(pin, map.getKey(), map.getValue(), null);
        }
        ScreenContext.setPins(pin);
        canvas.save();
        super.onDraw(canvas);
    }
}
