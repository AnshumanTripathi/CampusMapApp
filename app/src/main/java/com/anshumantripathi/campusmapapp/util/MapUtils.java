package com.anshumantripathi.campusmapapp.util;

public class MapUtils {

    /*This method will prepare the Google API UrL to obtain the distance and time between two points.*/
    public static String prepareDistanceMatrixURL(
            double src_lat,
            double src_long,
            double des_lat,
            double des_long,
            String mode
    ) {
        String stringURL = "";
        try {
            stringURL += "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=" +
                    Double.toString(src_lat) +
                    "," +
                    Double.toString(src_long) +
                    "&destinations=" +
                    Double.toString(des_lat) +
                    "," +
                    Double.toString(des_long) +
                    "&mode=" +
                    mode+"&key=API_KEY";
            return stringURL;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
