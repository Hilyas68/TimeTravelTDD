package uk.gov.dwp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.DayOfWeek;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.dwp.logic.TravelTimeDataInterface;

@ExtendWith(MockitoExtension.class)
public class TimeTravelCalculatorTest {

  @Mock
  TravelTimeDataInterface travelTimeData;
  private TimeTravelCalculator timeTravelCalculator;

  @BeforeEach
  public void setup() {
    timeTravelCalculator = new TimeTravelCalculator(travelTimeData);
  }

  @Test
  @DisplayName("Given that the same value as travelFrom and travelTo then return '00:00'")
  public void givenSameToAndFromReturnZeroHourAndMinute() {

    givenTravelTIme("Manchester", "Manchester", "00:00");
    String time = timeTravelCalculator.getTravelTime("Manchester", "Manchester");
    assertEquals("00:00", time, "Should return zero");
  }

  @Test
  @DisplayName("Given destination travel time has not been submitted then return '00:00'")
  public void givenNoTravelTimeForDestinationReturnZero() {

    givenTravelTIme("Leeds", "Manchester", "00:00");
    String time = timeTravelCalculator.getTravelTime("Leeds", "Manchester");
    assertEquals("00:00", time, "Should return zero '00:00' ");
  }

  @Test
  @DisplayName("Given a travel time data, then setTravelTime is called")
  public void givenTravelTimeValueIsRecorded() {
    timeTravelCalculator.setTravelTime("London", "Newcastle", "2:01");
    verify(travelTimeData, times(1)).setTravelTime(anyString(), anyString(), anyString());

  }

  private void givenTravelTIme(String travelFromLocation, String travelToLocation, String time) {
    when(travelTimeData.getTravelTime(travelFromLocation, travelToLocation)).thenReturn(time);
  }
}
