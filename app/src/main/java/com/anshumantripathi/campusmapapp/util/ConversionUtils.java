package com.anshumantripathi.campusmapapp.util;

import android.location.Location;

import com.anshumantripathi.campusmapapp.model.Coordinates;

/**
 * Created by saurabg on 10/28/16.
 */

public class ConversionUtils {

    Coordinates xy1_, xy2_, xy3_, xy4_;
    Coordinates ab1_, ab2_, ab3_, ab4_;
    // xy are real world coordinates and ab are pixel map coordinates
    ConversionUtils(Coordinates xy1, Coordinates xy2, Coordinates xy3, Coordinates xy4,
                    Coordinates ab1, Coordinates ab2, Coordinates ab3, Coordinates ab4) {
        this.xy1_ = xy1;
        this.xy2_ = xy2;
        this.xy3_ = xy3;
        this.xy4_ = xy4;
        this.ab1_ = ab1;
        this.ab2_ = ab2;
        this.ab3_ = ab3;
        this.ab4_ = ab4;
    }

    public Coordinates coordinatesToPixels(float xc, float yc) {
        double Y = Math.abs(xy2_.getLat() - xy1_.getLat());
        double X = Math.abs(xy3_.getLng() - xy1_.getLng());
        double B = Math.abs(ab2_.getLat() - ab1_.getLat());
        double A = Math.abs(ab3_.getLng() - ab1_.getLng());
        double r1 = 0, r2 = 0;
        if (X != 0 && Y != 0) {
            r1 = ((xc - xy1_.getLng()) * A)/X + ab1_.getLng();
            r2 = ((yc - xy1_.getLat()) * B)/Y + ab1_.getLat();
        }
        return new Coordinates(r1, r2);
    }

//    public double getCurrentPixelX(Location upperLeft, Location lowerRight, Location current) {
//        double hypotenuse = upperLeft.distanceTo(current);
//        double bearing = upperLeft.bearingTo(current);
//        double currentDistanceX = Math.sin(bearing * Math.PI / OneEightyDeg) * hypotenuse;
//        //                           "percentage to mark the position"
//        double totalHypotenuse = upperLeft.distanceTo(lowerRight);
//        double totalDistanceX = totalHypotenuse * Math.sin(upperLeft.bearingTo(lowerRight) * Math.PI / OneEightyDeg);
//        double currentPixelX = currentDistanceX / totalDistanceX * ImageSizeW;
//
//        return currentPixelX;
//    }
//
//    public double getCurrentPixelY(Location upperLeft, Location lowerRight, Location current) {
//        double hypotenuse = upperLeft.distanceTo(current);
//        double bearing = upperLeft.bearingTo(current);
//        double currentDistanceY = Math.cos(bearing * Math.PI / OneEightyDeg) * hypotenuse;
//        //                           "percentage to mark the position"
//        double totalHypotenuse = upperLeft.distanceTo(lowerRight);
//        double totalDistanceY = totalHypotenuse * Math.cos(upperLeft.bearingTo(lowerRight) * Math.PI / OneEightyDeg);
//        double currentPixelY = currentDistanceY / totalDistanceY * ImageSizeH;
//
//        return currentPixelY;
//    }

}
