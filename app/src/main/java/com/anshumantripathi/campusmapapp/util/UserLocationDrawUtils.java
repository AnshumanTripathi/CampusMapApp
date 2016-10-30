package com.anshumantripathi.campusmapapp.util;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.activities.MainActivity;
import com.anshumantripathi.campusmapapp.model.Pin;
import com.anshumantripathi.campusmapapp.model.RedDot;

public class UserLocationDrawUtils {
    public static void drawUserAtPixel(Context context, FrameLayout fLayout, int xP, int yP) {
        RedDot locationDot = new RedDot(context, xP, yP);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        fLayout.addView(locationDot, params);
    }
}
