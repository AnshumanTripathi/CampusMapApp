package com.anshumantripathi.campusmapapp.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.view.View;

import com.anshumantripathi.campusmapapp.util.LocationContext;
import com.anshumantripathi.campusmapapp.util.ScreenContext;

public class RedDot extends View {
    Paint mPaint;
    LocationContext ctx;
    int xP_, yP_;
    public RedDot(Context context, int xP, int yP) {
        super(context);
        setWillNotDraw(false);
        mPaint = new Paint();
        this.xP_ = xP;
        this.yP_ = yP;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // clear the existing circle
//        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(xP_, yP_, 30, mPaint);
        canvas.save();
        canvas.restore();
        super.onDraw(canvas);
    }
}
