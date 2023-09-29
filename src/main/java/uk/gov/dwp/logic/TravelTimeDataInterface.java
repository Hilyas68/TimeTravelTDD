package uk.gov.dwp.logic;

public interface TravelTimeDataInterface {

  String getTravelTime(String travelFromLocation, String travelToLocation);

  void setTravelTime(String travelFromLocation, String travelToLocation, String time);

  String getTravelDestinations(String leeds);

  String getTravelLocations();
}
