package com.anshumantripathi.campusmapapp.activities.Handlers;

import android.content.Context;
import android.widget.Toast;

public class GenericToastManager {
    public static void showLocationUnavailableError(Context ctx) {
        Toast.makeText(ctx, "User Location unavailable.", Toast.LENGTH_SHORT).show();
    }

    public static void showInvalidTargetError(Context ctx) {

    }

    public static void showGenericMsg(Context ctx, String Msg) {
        System.out.println(Msg);
        Toast.makeText(ctx, Msg, Toast.LENGTH_SHORT).show();
    }
}
