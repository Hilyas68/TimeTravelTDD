package uk.gov.dwp;

import uk.gov.dwp.logic.TravelTimeDataInterface;

public class TimeTravelCalculator {

  private TravelTimeDataInterface travelTimeData;

  public TimeTravelCalculator(TravelTimeDataInterface travelTimeData) {
    this.travelTimeData = travelTimeData;
  }

  public String getTravelTime(String travelFromLocation, String travelToLocation) {
    return travelTimeData.getTravelTime(travelFromLocation, travelToLocation);
  }
}
