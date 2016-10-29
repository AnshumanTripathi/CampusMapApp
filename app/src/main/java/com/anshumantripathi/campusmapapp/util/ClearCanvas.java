package com.anshumantripathi.campusmapapp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by AnshumanTripathi on 10/29/16.
 */

public class ClearCanvas extends View {
    Logger log = Logger.getLogger(ClearCanvas.class.getName());
    public ClearCanvas(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        log.info("On Draw Called");
        ArrayList<Bitmap> pins = ScreenContext.getPins();
        for(Bitmap pin : pins){
            pin.eraseColor(Color.TRANSPARENT);
        }
        ScreenContext.clearPins();
        super.onDraw(canvas);
    }
}
