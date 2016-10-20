package com.anshumantripathi.campusmapapp.model;

import java.util.ArrayList;
public class CampusData {

    private static Coordinates point1;
    private static Coordinates point2;
    private static Coordinates point3;
    private static Coordinates point4;
    public static ArrayList<BuildingData> buildingData;

    public ArrayList<BuildingData> getBuildingData() {
        return buildingData;
    }

    public CampusData() {
        populateBuildingDetails();
    }

    private void populateBuildingDetails() {
        buildingData = new ArrayList<>();
        BuildingData bd = new BuildingData();
        bd.setName("Engineering Building");
        bd.setAddress("San Jose Campus");
        bd.setLat(37.338208);
        bd.setLng(-121.886329);

        buildingData.add(bd);
    }

    public void setBuildingData(ArrayList<BuildingData> userBuildingData) {
        buildingData = userBuildingData;
    }

    public Coordinates getPoint1() {
        return point1;
    }

    public void setPoint1(Coordinates userPoint1) {
        point1 = userPoint1;
    }

    public Coordinates getPoint2() {
        return point2;
    }

    public void setPoint2(Coordinates userPoint2) {
        point2 = userPoint2;
    }

    public Coordinates getPoint3() {
        return point3;
    }

    public void setPoint3(Coordinates userPoint3) {
        point3 = userPoint3;
    }

    public Coordinates getPoint4() {
        return point4;
    }

    public void setPoint4(Coordinates userPoint4) {
        point4 = userPoint4;
    }

    public static void initCampusBoundaries() {

        //4th and San Fernando
        point1 = new Coordinates();
        point1.setLat(37.335848);
        point1.setLng(-121.886039);

        //10th and San Fernando
        point2 = new Coordinates();
        point2.setLat(37.338893);
        point2.setLng(-121.879698);

        //10th and E. San Salvador
        point3 = new Coordinates();
        point3.setLat(37.334557);
        point3.setLng(-121.876453);

        //4th and E. San Salvador
        point4 =  new Coordinates();
        point4.setLat(37.331550);
        point4.setLng(-121.882851);

    }
    
}
