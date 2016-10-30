package com.anshumantripathi.campusmapapp.util;

import android.location.Location;

import com.anshumantripathi.campusmapapp.model.Constants;
import com.anshumantripathi.campusmapapp.model.Coordinates;

public class ConversionUtils {

    Location xy1_, xy2_, xy3_, xy4_;
    double real_world_width = 0;
    double real_world_height = 0;

    // xy are real world coordinates and ab are pixel map coordinates
    public ConversionUtils(Coordinates xy1, Coordinates xy2, Coordinates xy3, Coordinates xy4) {
        xy1_ = new Location("");
        xy1_.setLatitude(xy1.getLat());
        xy1_.setLongitude(xy1.getLng());

        xy2_ = new Location("");
        xy2_.setLatitude(xy2.getLat());
        xy2_.setLongitude(xy2.getLng());

        xy3_ = new Location("");
        xy3_.setLatitude(xy3.getLat());
        xy3_.setLongitude(xy3.getLng());

        xy4_ = new Location("");
        xy4_.setLatitude(xy4.getLat());
        xy4_.setLongitude(xy4.getLng());

        real_world_width = Math.abs(xy1_.distanceTo(xy2_));
        real_world_height = Math.abs(xy1_.distanceTo(xy3_));
    }

    public Coordinates coorToPixels(Coordinates current) {
        Coordinates ret = new Coordinates();

        Location currentL = new Location("");
        currentL.setLatitude(current.getLat());
        currentL.setLongitude(current.getLng());

        double K = Math.abs(xy1_.distanceTo(currentL));
        double M = Math.abs(xy3_.distanceTo(currentL));
        double N = this.real_world_height;

        double X = (K*K - M*M + N*N)/(2*N);
        double real_world_width_currL = Math.sqrt(Math.abs(K*K - X*X));

        ret.setLng(Constants.xPixelOffset +
                real_world_width_currL * Constants.ImageSizeW / real_world_width);
        ret.setLat(Constants.yPixelOffset + X * Constants.ImageSizeH / real_world_height);
        return ret;

    }
}
