package com.anshumantripathi.campusmapapp.util;

import android.location.Location;

import com.anshumantripathi.campusmapapp.model.Coordinates;

public class ConversionUtils {

    public final static double OneEightyDeg = 180.0d;
    public static double ImageSizeW = 1300, ImageSizeH = 1500;

    Location xy1_, xy2_, xy3_, xy4_;
    double real_world_width = 0;
    double real_world_height = 0;
//    Coordinates ab1_, ab2_, ab3_, ab4_;
    // xy are real world coordinates and ab are pixel map coordinates
    public ConversionUtils(Coordinates xy1, Coordinates xy2, Coordinates xy3, Coordinates xy4) {
//                    Coordinates ab1, Coordinates ab2, Coordinates ab3, Coordinates ab4) {
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

//        double t = xy1_.distanceTo(xy2_);
        double t1 = this.findDistance(xy1_.getLatitude(), 37.3366468, xy1_.getLongitude(),
                -121.8843052, 0, 0);

        real_world_width = Math.abs(xy1_.distanceTo(xy2_));
        real_world_height = Math.abs(xy1_.distanceTo(xy3_));
    }


//    public double coorToYPixel(Coordinates current) {
//        Location currentL = new Location("");
//        currentL.setLatitude(current.getLat());
//        currentL.setLongitude(current.getLng());
//
//        double A = Math.abs(xy2_.distanceTo(currentL));
//        double B = Math.abs(xy1_.distanceTo(currentL));
//        double C = this.real_world_width;
//
//        double X = (A*A - B*B + C*C)/(2*C);
//        double real_world_height_currL = Math.sqrt(Math.abs(A*A - X*X));
//
//        return real_world_height_currL * ImageSizeH / real_world_height;
//    }

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

        ret.setLng(real_world_width_currL * ImageSizeW / real_world_width);
        ret.setLat(X * ImageSizeH / real_world_height);
        return ret;

    }

//    public double coorToXPixel(Coordinates current) {
//    }
//
//    public double getCurrentPixelX(Coordinates current) {
//        Location upperLeft = new Location("");
//        upperLeft.setLatitude(xy1_.getLat());
//        upperLeft.setLongitude(xy1_.getLng());
//
//        Location lowerRight = new Location("");
//        lowerRight.setLatitude(xy4_.getLat());
//        lowerRight.setLongitude(xy4_.getLng());
//
//        Location currentL = new Location("");
//        currentL.setLatitude(current.getLat());
//        currentL.setLongitude(current.getLng());
//
//        double hypotenuse = upperLeft.distanceTo(currentL);
//        double bearing = upperLeft.bearingTo(currentL);
//        double currentDistanceX = Math.sin(bearing * Math.PI / OneEightyDeg) * hypotenuse;
//        double totalHypotenuse = upperLeft.distanceTo(lowerRight);
//        double totalDistanceX = totalHypotenuse * Math.sin(upperLeft.bearingTo(lowerRight) * Math.PI / OneEightyDeg);
//        double currentPixelX = currentDistanceX / totalDistanceX * ImageSizeW;
//
//        return currentPixelX;
//    }
//
//    public double getCurrentPixelY(Coordinates current) {
//        Location upperLeft = new Location("");
//        upperLeft.setLatitude(xy1_.getLat());
//        upperLeft.setLongitude(xy1_.getLng());
//
//        Location lowerRight = new Location("");
//        lowerRight.setLatitude(xy4_.getLat());
//        lowerRight.setLongitude(xy4_.getLng());
//
//        Location currentL = new Location("");
//        currentL.setLatitude(current.getLat());
//        currentL.setLongitude(current.getLng());
//
//        double hypotenuse = upperLeft.distanceTo(currentL);
//        double bearing = upperLeft.bearingTo(currentL);
//        double currentDistanceY = Math.cos(bearing * Math.PI / OneEightyDeg) * hypotenuse;
//        double totalHypotenuse = upperLeft.distanceTo(lowerRight);
//        double totalDistanceY = totalHypotenuse * Math.cos(upperLeft.bearingTo(lowerRight) * Math.PI / OneEightyDeg);
//        double currentPixelY = currentDistanceY / totalDistanceY * ImageSizeH;
//
//        return currentPixelY;
//    }

    //    public Coordinates coordinatesToPixels(double xc, double yc) {
//        double Y = Math.abs(xy2_.getLat() - xy1_.getLat());
//        double X = Math.abs(xy3_.getLng() - xy1_.getLng());
////        double B = Math.abs(ab2_.getLat() - ab1_.getLat());
////        double A = Math.abs(ab3_.getLng() - ab1_.getLng());
//        double B = ImageSizeH;
//        double A = ImageSizeW;
//        double r1 = 0, r2 = 0;
//        if (X != 0 && Y != 0) {
////            r1 = ((xc - xy1_.getLng()) * A)/X + ab1_.getLng();
////            r2 = ((yc - xy1_.getLat()) * B)/Y + ab1_.getLat();
//            r1 = ((xc - xy1_.getLng()) * A)/X ;
//            r2 = ((yc - xy1_.getLat()) * B)/Y;
//        }
//        return new Coordinates(r1, r2);
//    }

    public double findDistance(double lat1, double lat2, double lon1,
                               double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

}
