package uk.gov.dwp.logic;

public class TravelTimeData implements TravelTimeDataInterface {

  @Override
  public String getTravelTime(String travelFromLocation, String travelToLocation) {
    return "00:00";
  }
}
