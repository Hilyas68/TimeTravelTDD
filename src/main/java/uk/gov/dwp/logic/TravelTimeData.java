package uk.gov.dwp.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class TravelTimeData implements TravelTimeDataInterface {

  public static final String DEFAULT_TIME = "00:00";
  public Map<String, Map<String, String>> travelTimes;

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

      Map<String, String> fromMap = travelTimes.get(travelFromLocation);
      Map<String, String> toMap = travelTimes.get(travelToLocation);

      if (Objects.nonNull(fromMap) && Objects.nonNull(toMap)) {
        int totalMinutes = parseTravelTime(time) + parseTravelTime(
            fromMap.getOrDefault(travelToLocation, DEFAULT_TIME));
        int averageTimeInMinutes = (totalMinutes + 1) / 2;
        String averageTimeValue = formatTravelTime(averageTimeInMinutes);

        fromMap.put(travelToLocation, averageTimeValue);
        toMap.put(travelToLocation, averageTimeValue);
      } else {
        travelTimes.computeIfAbsent(travelFromLocation, k -> new HashMap<>())
            .put(travelToLocation, time);
        travelTimes.computeIfAbsent(travelToLocation, k -> new HashMap<>())
            .put(travelFromLocation, time);
      }
    }
  }

  private static String formatTravelTime(int averageTime) {
    int averagedHour = averageTime / 60;
    int averagedMinute = averageTime % 60;
    return String.format("%s:%s", averagedHour, averagedMinute);
  }

  private int parseTravelTime(String travelTime) {
    String[] timePart = travelTime.split(":");
    int hours = Integer.parseInt(timePart[0]);
    int minutes = Integer.parseInt(timePart[1]);
    return hours * 60 + minutes;
  }

  @Override
  public String getTravelDestinations(String locations) {
    Map<String, String> destinations = travelTimes.get(locations);
    if (destinations != null) {
      return String.join(",", destinations.keySet());
    }
    return "";
  }

  @Override
  public String getTravelLocations() {
    Set<String> sortedLocations = new TreeSet<>(travelTimes.keySet());
    return String.join(",", sortedLocations);
  }
}
