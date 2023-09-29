package uk.gov.dwp.logic;

import java.util.HashMap;
import java.util.Map;

public class TravelTimeData implements TravelTimeDataInterface {

  public static final String DEFAULT_TIME = "00:00";
  Map<String, Map<String, String>> travelTimes;

  public TravelTimeData() {
    this.travelTimes = new HashMap<>();
  }

  @Override
  public String getTravelTime(String travelFromLocation, String travelToLocation) {
    if (travelFromLocation.equals(travelToLocation)) {
      return DEFAULT_TIME;
    }
    Map<String, String> fromMap = travelTimes.getOrDefault(travelFromLocation, new HashMap<>());

    return fromMap.getOrDefault(travelToLocation, DEFAULT_TIME);
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

  @Override
  public String getTravelDestinations(String locations) {
    Map<String, String> destinations = travelTimes.get(locations);
    if (destinations != null) {
      return String.join(",", destinations.keySet());
    }
    return "";
  }
}
