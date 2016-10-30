package com.anshumantripathi.campusmapapp.util;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.anshumantripathi.campusmapapp.model.RedDot;
import com.anshumantripathi.campusmapapp.model.UserDotsClear;

public class UserLocationDrawUtils {
    public static void drawUserAtPixel(Context context, FrameLayout fLayout, int xP, int yP) {
        RedDot locationDot = new RedDot(context, xP, yP);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        fLayout.addView(locationDot, params);
    }

    public static void clearUserDots(Context context, FrameLayout frameLayout) {
        UserDotsClear clear = new UserDotsClear(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        frameLayout.addView(clear,params);
    }
}
