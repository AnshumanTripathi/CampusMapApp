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
import com.anshumantripathi.campusmapapp.util.ScreenContext;

import java.util.logging.Logger;

public class RedDot extends View {

    Bitmap uL_;
    Paint mPaint;
    LocationContext ctx;
    Logger log = Logger.getLogger(RedDot.class.getName());
    int xP_, yP_;
    public RedDot(Context context, int xP, int yP) {
        super(context);
        setWillNotDraw(false);
        mPaint = new Paint();
        this.xP_ = xP;
        this.yP_ = yP;
        setWillNotDraw(false);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inMutable = true;
        uL_ = BitmapFactory.decodeResource(getResources(), R.drawable.user_location,opts);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        log.info("onDraw Called!"+"xP: "+xP_+ " yP: "+yP_);
        // clear existing user location first
        ScreenContext.addUserDot(uL_);
        canvas.drawBitmap(uL_, xP_, yP_, null);
        canvas.save();
        super.onDraw(canvas);
    }
}
