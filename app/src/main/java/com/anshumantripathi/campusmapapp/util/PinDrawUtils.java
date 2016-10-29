package com.anshumantripathi.campusmapapp.util;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.activities.MainActivity;
import com.anshumantripathi.campusmapapp.model.Pin;

public class PinDrawUtils {

    public static void drawPinAtPixel(Context context, FrameLayout frameLayout) {
        Pin pinLoc = new Pin(context);
        FrameLayout.LayoutParams paramsP = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        frameLayout.addView(pinLoc, paramsP);
    }

    public static void clearPinAtPixel(Context context, FrameLayout frameLayout) {
        ClearCanvas clear = new ClearCanvas(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        frameLayout.addView(clear,params);
    }
}
