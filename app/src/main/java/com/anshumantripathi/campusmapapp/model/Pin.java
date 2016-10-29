package com.anshumantripathi.campusmapapp.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.util.LocationContext;

/**
 * Created by AnshumanTripathi on 10/24/16.
 */

public class Pin extends View {

    Bitmap pin;
    Paint mPaint;
    int xP, yP;

    public Pin(Context context, int xPixel, int yPixel) {
        super(context);
        this.xP = xPixel;
        this.yP = yPixel;
        setWillNotDraw(false);
        pin = BitmapFactory.decodeResource(getResources(), R.drawable.pin);
        mPaint = new Paint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        System.out.print("onDraw Called!");
        canvas.drawBitmap(pin,xP, yP, null);

        canvas.save();
        canvas.restore();
        super.onDraw(canvas);

    }
}
