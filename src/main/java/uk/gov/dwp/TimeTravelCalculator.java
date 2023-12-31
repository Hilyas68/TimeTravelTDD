package uk.gov.dwp;

import uk.gov.dwp.logic.TravelTimeDataInterface;

public class TimeTravelCalculator {

  private final TravelTimeDataInterface travelTimeData;

  public TimeTravelCalculator(TravelTimeDataInterface travelTimeData) {
    this.travelTimeData = travelTimeData;
  }

  public String getTravelTime(String travelFromLocation, String travelToLocation) {
    return travelTimeData.getTravelTime(travelFromLocation, travelToLocation);
  }

  public void setTravelTime(String travelFromLocation, String travelToLocation, String time) {
    this.travelTimeData.setTravelTime(travelFromLocation, travelToLocation, time);
  }

  public String getTravelLocations() {
    return travelTimeData.getTravelLocations();
  }

  public String getTravelDestinations(String location) {
    return travelTimeData.getTravelDestinations(location);
  }
}
