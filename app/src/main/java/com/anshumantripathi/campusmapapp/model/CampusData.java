package com.anshumantripathi.campusmapapp.model;

import com.anshumantripathi.campusmapapp.R;

import java.util.ArrayList;
public class CampusData {

    private Coordinates point1;
    private Coordinates point2;
    private Coordinates point3;
    private Coordinates point4;
    public ArrayList<BuildingData> buildingData;
    public ArrayList<String> buildingName;

    public ArrayList<BuildingData> getBuildingData() {
        return buildingData;
    }

    public CampusData() {
        populateBuildingDetails();
    }

    private void populateBuildingDetails() {
        buildingData = new ArrayList<>();

        BuildingData bd_1 = new BuildingData();
        bd_1.setName("King Library");
        bd_1.setAddress("Dr. Martin Luther King, Jr. Library, 150 East San Fernando Street, San Jose, CA 95112");

        bd_1.setStreetAddress("Dr. Martin Luther King, Jr. Library, 150 East San Fernando Street, San Jose, CA 95112");
        bd_1.setLat(37.335507);
        bd_1.setLng(-121.884999);
        bd_1.setHeading(-1);
        bd_1.setBimage(R.drawable.library);
        buildingData.add(bd_1);

        BuildingData bd_2 = new BuildingData();
        bd_2 = new BuildingData();
        bd_2.setName("Engineering Building");
        bd_2.setAddress("San José State University Charles W. Davidson College of Engineering, 1 Washington Square, San Jose, CA 95112");
        bd_2.setStreetAddress("E San Fernando St and S 7th St,San Jose,CA 95112,USA");
        bd_2.setLat(37.335142);
        bd_2.setLng(-121.881276);
        bd_2.setHeading(144.29);
        bd_2.setBimage(R.drawable.eng_building);
        buildingData.add(bd_2);

        BuildingData bd_3 = new BuildingData();
        bd_3 = new BuildingData();
        bd_3.setName("Yoshihiro Uchida Hall");
        bd_3.setAddress("Yoshihiro Uchida Hall, San Jose, CA 95112");
        bd_3.setStreetAddress("Yoshihiro Uchida Hall, San Jose, CA 95112");
        bd_3.setLat(37.333770);
        bd_3.setLng(-121.883388);
        bd_3.setHeading(57.8);
        bd_3.setBimage(R.drawable.ychall);
        buildingData.add(bd_3);

        BuildingData bd_4 = new BuildingData();
        bd_4.setName("Student Union");
        bd_4.setAddress("Student Union Building, San Jose, CA 95112");
        bd_4.setStreetAddress("Student Union Building, San Jose, CA 95112");
        bd_4.setLat(37.424197);
        bd_4.setLng(-122.170939);
        bd_4.setHeading(146.06);
        bd_4.setBimage(R.drawable.studentunion);
        buildingData.add(bd_4);

        BuildingData bd_5 = new BuildingData();
        bd_5.setName("BBC");
        bd_5.setAddress("Boccardo Business Complex, San Jose, CA 95112");
        bd_5.setStreetAddress("237 S tenth street san jose CA 95112");
        bd_5.setLat(37.336561);
        bd_5.setLng(-121.878723);
        bd_5.setHeading(-121.31);
        bd_5.setBimage(R.drawable.bbc);
        buildingData.add(bd_5);

        BuildingData bd_6 = new BuildingData();
        bd_6.setName("South Parking Garage");
        bd_6.setAddress("San Jose State University South Garage, 330 South 7th Street, San Jose, CA 95112");
        bd_6.setStreetAddress("San Jose State University South Garage, 330 South 7th Street, San Jose, CA 95112");
        bd_6.setLat(37.333474);
        bd_6.setLng(-121.879916);
        bd_6.setHeading(-1);
        bd_6.setBimage(R.drawable.garage);
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

    }
    
}
