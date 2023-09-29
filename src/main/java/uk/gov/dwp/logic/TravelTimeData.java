package uk.gov.dwp.logic;

import java.util.HashMap;
import java.util.Map;

public class TravelTimeData implements TravelTimeDataInterface {

  Map<String, Map<String, String>> travelTimes;

  public TravelTimeData() {
    this.travelTimes = new HashMap<>();
  }

  @Override
  public String getTravelTime(String travelFromLocation, String travelToLocation) {
    if (travelFromLocation.equals(travelToLocation)) {
      return "00:00";
    }
    Map<String, String> fromMap = travelTimes.getOrDefault(travelFromLocation, new HashMap<>());

    return fromMap.getOrDefault(travelToLocation, "00:00");
  }

  @Override
  public void setTravelTime(String travelFromLocation, String travelToLocation, String time) {

    if (!travelFromLocation.equals(travelToLocation)) {
      travelTimes.computeIfAbsent(travelFromLocation, k -> new HashMap<>())
          .put(travelToLocation, time);
      travelTimes.computeIfAbsent(travelToLocation, k -> new HashMap<>())
          .put(travelFromLocation, time);
    }
  }
}
