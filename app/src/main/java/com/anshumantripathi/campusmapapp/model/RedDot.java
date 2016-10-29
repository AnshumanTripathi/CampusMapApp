package com.anshumantripathi.campusmapapp.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.anshumantripathi.campusmapapp.util.LocationContext;

/**
 * Created by AnshumanTripathi on 10/27/16.
 */

public class RedDot extends View {
    Paint mPaint;
    LocationContext ctx;
    public RedDot(Context context) {
        super(context);
        setWillNotDraw(false);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        ctx = LocationContext.getInstance();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(ctx.getxPixel(),ctx.getyPixel(),30,mPaint);
        canvas.save();
        canvas.restore();
        super.onDraw(canvas);
    }
}
