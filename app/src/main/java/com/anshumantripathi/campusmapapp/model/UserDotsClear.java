package com.anshumantripathi.campusmapapp.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

import com.anshumantripathi.campusmapapp.util.ClearCanvas;
import com.anshumantripathi.campusmapapp.util.ScreenContext;

import java.util.logging.Logger;

public class UserDotsClear extends View {
    Logger log = Logger.getLogger(ClearCanvas.class.getName());
    public UserDotsClear(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(Bitmap dot: ScreenContext.userDots){
            dot.eraseColor(Color.TRANSPARENT);
        }
        ScreenContext.clearUserDots();
        super.onDraw(canvas);
    }

}
