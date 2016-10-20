package com.anshumantripathi.campusmapapp.Model;

import java.util.ArrayList;

public class CampusData {

    private static Coordiantes point1;
    private static Coordiantes point2;
    private static Coordiantes point3;
    private static Coordiantes point4;
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

    public void setBuildingData(ArrayList<BuildingData> buildingData) {
        this.buildingData = buildingData;
    }

    public Coordiantes getPoint1() {
        return point1;
    }

    public void setPoint1(Coordiantes point1) {
        this.point1 = point1;
    }

    public Coordiantes getPoint2() {
        return point2;
    }

    public void setPoint2(Coordiantes point2) {
        this.point2 = point2;
    }

    public Coordiantes getPoint3() {
        return point3;
    }

    public void setPoint3(Coordiantes point3) {
        this.point3 = point3;
    }

    public Coordiantes getPoint4() {
        return point4;
    }

    public void setPoint4(Coordiantes point4) {
        this.point4 = point4;
    }

    public static void initCampusBoundaries() {

        //4th and San Fernando
        point1 = new Coordiantes();
        point1.setLat(37.335848);
        point1.setLng(-121.886039);

        //10th and San Fernando
        point2 = new Coordiantes();
        point2.setLat(37.338893);
        point2.setLng(-121.879698);

        //10th and E. San Salvador
        point3 = new Coordiantes();
        point3.setLat(37.334557);
        point3.setLng(-121.876453);

        //4th and E. San Salvador
        point4 =  new Coordiantes();
        point4.setLat(37.331550);
        point4.setLng(-121.882851);

    }
    
}
