package com.anshumantripathi.campusmapapp.model;

import com.anshumantripathi.campusmapapp.R;
import com.anshumantripathi.campusmapapp.util.ConversionUtils;

import java.util.ArrayList;
public class CampusData {

    private Coordinates point1;
    private Coordinates point2;
    private Coordinates point3;
    private Coordinates point4;
    private ArrayList<BuildingData> buildingData;

    public ConversionUtils convUtils = null;

    public ArrayList<BuildingData> getBuildingData() {
        return buildingData;
    }

    public CampusData() {
        populateBuildingDetails();
        initCampusBoundaries();
    }

    private void populateBuildingDetails() {
        buildingData = new ArrayList<>();

        BuildingData bd_1 = new BuildingData();
        bd_1.setName("King Library");
        bd_1.setAddress("Dr. Martin Luther King, Jr. Library, 150 East San Fernando Street, San Jose, CA 95112");
        bd_1.setLat(37.3354338);
        bd_1.setLng(-121.8850354);
        bd_1.setBimage(R.drawable.library);
        bd_1.setStreetViewCoord(new Coordinates(37.335785,-121.885790));
        bd_1.setxPixel(195);
        bd_1.setyPixel(632);
        bd_1.setAbbr("king");
        buildingData.add(bd_1);

        BuildingData bd_2 = new BuildingData();
        bd_2 = new BuildingData();
        bd_2.setName("Engineering Building");
        bd_2.setAddress("San José State University Charles W. Davidson College of Engineering, 1 Washington Square, San Jose, CA 95112");
        bd_2.setLat(37.337383);
        bd_2.setLng(-121.881692);
        bd_2.setBimage(R.drawable.eng_building);
        bd_2.setStreetViewCoord(new Coordinates(37.337404,-121.882614));
        bd_2.setxPixel(826);
        bd_2.setyPixel(637);
        bd_2.setAbbr("eng");
        buildingData.add(bd_2);

        BuildingData bd_3 = new BuildingData();
        bd_3 = new BuildingData();
        bd_3.setName("Yoshihiro Uchida Hall");
        bd_3.setAddress("Yoshihiro Uchida Hall, San Jose, CA 95112");
        bd_3.setLat(37.333695);
        bd_3.setLng(-121.883834);
        bd_3.setStreetViewCoord(new Coordinates(37.333362,-121.884132));
        bd_3.setBimage(R.drawable.ychall);
        bd_3.setAbbr("yuh");
        bd_3.setxPixel(165);
        bd_3.setyPixel(1297);
        buildingData.add(bd_3);

        BuildingData bd_4 = new BuildingData();
        bd_4.setName("Student Union");
        bd_4.setAddress("Student Union Building, San Jose, CA 95112");
        bd_4.setLat(37.336292);
        bd_4.setLng(-121.881363);
        bd_4.setStreetViewCoord(new Coordinates(37.337247,-121.882780));
        bd_4.setBimage(R.drawable.studentunion);
        bd_4.setxPixel(868);
        bd_4.setyPixel(852);
        bd_4.setAbbr("su");

        buildingData.add(bd_4);

        BuildingData bd_5 = new BuildingData();
        bd_5.setName("Boccardo Business Complex");
        bd_5.setAddress("Boccardo Business Complex, San Jose, CA 95112");
        bd_5.setLat(37.336673);
        bd_5.setLng(-121.878591);
        bd_5.setStreetViewCoord(new Coordinates(37.336855,-121.878296));
        bd_5.setBimage(R.drawable.bbc);
        bd_5.setAbbr("bbc");
        bd_5.setxPixel(1257);
        bd_5.setyPixel(1042);
        buildingData.add(bd_5);

        BuildingData bd_6 = new BuildingData();
        bd_6.setName("South Parking Garage");
        bd_6.setAddress("San Jose State University South Garage, 330 South 7th Street, San Jose, CA 95112");
        bd_6.setLat(37.333044);
        bd_6.setLng(-121.880983);
        bd_6.setStreetViewCoord(new Coordinates(37.332687,-121.880516));
        bd_6.setBimage(R.drawable.garage);
        bd_6.setAbbr("spg");
        bd_6.setxPixel(559);
        bd_6.setyPixel(1812);
        buildingData.add(bd_6);
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

    public void initCampusBoundaries() {

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

        this.convUtils = new ConversionUtils(point1, point2, point4, point3);

    }
    
}
