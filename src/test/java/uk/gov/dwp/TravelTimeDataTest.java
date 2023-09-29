package uk.gov.dwp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.dwp.logic.TravelTimeData;

public class TravelTimeDataTest {

  @Test
  @DisplayName("Given the same location, return 0 hour 0 minutes")
  public void givenSameLocationReturnZero() {
    TravelTimeData travelTimeData = new TravelTimeData();
    String time = travelTimeData.getTravelTime("Leeds", "Leeds");
    assertEquals("00:00", time, "should return '00:00'");
  }

  @Test
  @DisplayName("Given destination travel time has not been previously submitted then return '00:00'")
  public void givenNoTravelTimeForDestinationReturnZero() {
    TravelTimeData travelTimeData = new TravelTimeData();
    String time = travelTimeData.getTravelTime("Leeds", "Manchester");
    assertEquals("00:00", time, "should return '00:00'");
  }

  @Test
  @DisplayName("Given travel time between two location are set, it should return the correct travel time")
  public void setTravelTimeThenReturnTime() {
    TravelTimeData travelTimeData = new TravelTimeData();
    travelTimeData.setTravelTime("Leeds", "London", "2:17");
    String time = travelTimeData.getTravelTime("Leeds", "London");
    assertEquals("2:17", time, "should return same time");
  }

  @Test
  @DisplayName("Given travel time location is set, when you reverse the location it should returm same travel time")
  public void givenLocationIsReversedReturnSameTime() {
    TravelTimeData travelTimeData = new TravelTimeData();
    travelTimeData.setTravelTime("Leeds", "London", "2:17");
    String time = travelTimeData.getTravelTime("London", "Leeds");
    assertEquals("2:17", time, "should return same time after changing from to 'To'");
  }
}
