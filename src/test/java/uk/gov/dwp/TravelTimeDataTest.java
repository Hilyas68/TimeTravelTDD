package uk.gov.dwp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.dwp.logic.TravelTimeData;

public class TravelTimeDataTest {

  private TravelTimeData travelTimeData;

  @BeforeEach
  public void setup() {
    travelTimeData = new TravelTimeData();
  }

  @Test
  @DisplayName("Given the same location, return 0 hour 0 minutes")
  public void givenSameLocationReturnZero() {
    String time = travelTimeData.getTravelTime("Leeds", "Leeds");
    assertEquals("00:00", time, "should return '00:00'");
  }

  @Test
  @DisplayName("Given destination travel time has not been previously submitted then return '00:00'")
  public void givenNoTravelTimeForDestinationReturnZero() {
    String time = travelTimeData.getTravelTime("Leeds", "Manchester");
    assertEquals("00:00", time, "should return '00:00'");
  }

  @Test
  @DisplayName("Given travel time between two location are set, it should return the correct travel time")
  public void setTravelTimeThenReturnTime() {
    travelTimeData.setTravelTime("Leeds", "London", "2:17");
    String time = travelTimeData.getTravelTime("Leeds", "London");
    assertEquals("2:17", time, "should return same time");
  }

  @Test
  @DisplayName("Given travel time location is set, when you reverse the location it should returm same travel time")
  public void givenLocationIsReversedReturnSameTime() {
    travelTimeData.setTravelTime("Leeds", "London", "2:17");
    String time = travelTimeData.getTravelTime("London", "Leeds");
    assertEquals("2:17", time, "should return same time after changing from to 'To'");
  }

  @Test
  @DisplayName("Given travel data, when getting desstination for a location then return expected destinations")
  public void getTravelDestinations() {
    travelTimeData.setTravelTime("Leeds", "London", "2:17");
    travelTimeData.setTravelTime("Leeds", "Blackpool", "NA");
    travelTimeData.setTravelTime("Leeds", "Manchester", "1:06");
    String destinations = travelTimeData.getTravelDestinations("Leeds");
    assertEquals("Blackpool,London,Manchester", destinations,
        "should return list destinations separated by comma");
  }

  @Test
  @DisplayName("Given travel data, return all recognized locations")
  public void getAllLocation() {
    travelTimeData.setTravelTime("Leeds", "London", "2:17");
    travelTimeData.setTravelTime("Newcastle", "Blackpool", "NA");
    travelTimeData.setTravelTime("Birmingham", "Manchester", "1:06");
    String locations = travelTimeData.getTravelLocations();
    assertEquals("Birmingham,Blackpool,Leeds,London,Manchester,Newcastle", locations,
        "should return all locations");
  }

  @Test
  @DisplayName("Given two locations are set to the same, it should ignore")
  public void setSameLocationThenIgnore() {
    travelTimeData.setTravelTime("Leeds", "Leeds", "2:00");
    int dataSize = travelTimeData.travelTimes.size();
    assertEquals(0, dataSize, "should return same time");
  }

  @Test
  @DisplayName("Given two existing locations when travel time is modified then return average time")
  public void existingLocationsReturnAverage() {
    travelTimeData.setTravelTime("Leeds", "London", "2:15");
    travelTimeData.setTravelTime("Leeds", "London", "2:17");
    String time = travelTimeData.getTravelTime("Leeds", "London");
    assertEquals("2:16", time, "should return average time");

  }
}
