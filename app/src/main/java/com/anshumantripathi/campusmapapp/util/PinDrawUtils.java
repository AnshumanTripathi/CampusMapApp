package com.anshumantripathi.campusmapapp.util;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.activities.MainActivity;
import com.anshumantripathi.campusmapapp.model.Pin;

public class PinDrawUtils {

    public void drawPinAtPixel(Context context, FrameLayout flayout, int xPixel, int yPixel) {
        Pin pinLoc = new Pin(context, xPixel, yPixel);
        FrameLayout.LayoutParams paramsP = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        flayout.addView(pinLoc, paramsP);
    }

    public void clearPinAtPixel(Context context, int xPixel, int yPixel) {

    }
}
