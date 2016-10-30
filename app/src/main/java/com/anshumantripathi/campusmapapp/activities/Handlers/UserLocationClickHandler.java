package com.anshumantripathi.campusmapapp.activities.Handlers;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.model.Constants;
import com.anshumantripathi.campusmapapp.model.Coordinates;
import com.anshumantripathi.campusmapapp.util.LocationContext;
import com.anshumantripathi.campusmapapp.util.UserLocationDrawUtils;
import com.anshumantripathi.campusmapapp.util.UserLocationFinderHelper;

import java.util.logging.Logger;

public class UserLocationClickHandler implements View.OnClickListener {
    private AppCompatActivity appActivity;
    private LocationContext ctx;
    private Logger logger = Logger.getLogger(UserLocationClickHandler.class.getName());
    public UserLocationClickHandler (
            AppCompatActivity activity,
            LocationContext ctx
    ) {
        this.appActivity = activity;
        this.ctx = ctx;
    }

    @Override
    public void onClick(View v) {
        this.displayCurrentUserLocation(true);
    }

    public void displayCurrentUserLocation(boolean showErrorIfOutOfBoundary) {
        UserLocationDrawUtils.clearUserDots(appActivity,
                (FrameLayout) appActivity.findViewById(R.id.frameLayout));
        UserLocationFinderHelper.updateCurrentLocation(appActivity, ctx, showErrorIfOutOfBoundary);
        logger.info("Current Location from ctx: "+ctx.getCurrentLocation().toString());
        if (ctx.getCurrentLocation() != null) {
            System.out.println("user location is: " + ctx.getCurrentLocation().getLat() + " , " +
                ctx.getCurrentLocation().getLng());
            Coordinates mapPixels = ctx.cd.convUtils.coorToPixels(ctx.getCurrentLocation());
            if (mapPixels.getLng() > (Constants.xPixelOffset + Constants.ImageSizeW) ||
                    mapPixels.getLat() > (Constants.yPixelOffset + Constants.ImageSizeH)) {
                if (showErrorIfOutOfBoundary) {
                    GenericToastManager.showGenericMsg(
                            appActivity.getBaseContext(),
                            "User is out of Campus."
                    );
                }
            } else {
                UserLocationDrawUtils.drawUserAtPixel(appActivity,
                        (FrameLayout) appActivity.findViewById(R.id.frameLayout),
                        (int) mapPixels.getLng(),
                        (int) mapPixels.getLat());
            }
        }
    }
}
