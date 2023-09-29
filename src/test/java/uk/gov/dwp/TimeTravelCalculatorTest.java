package uk.gov.dwp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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

  @Test
  @DisplayName("Given that the same value as travelFrom and travelTo then return '00:00'")
  public void givenSameToAndFromReturnZeroHourAndMinute() {

    when(travelTimeData.getTravelTime("Leeds", "Leeds")).thenReturn("00:00");
    TimeTravelCalculator timeTravelCalculator = new TimeTravelCalculator(travelTimeData);
    String time = timeTravelCalculator.getTravelTime("Leeds", "Leeds");
    assertEquals("00:00", time, "Should return zero");
  }

  @Test
  @DisplayName("Given destination travel time has not been submitted then return '00:00'")
  public void givenNoTravelTimeForDestinationReturnZero() {

    when(travelTimeData.getTravelTime("Leeds", "Manchester")).thenReturn("00:00");
    TimeTravelCalculator timeTravelCalculator = new TimeTravelCalculator(travelTimeData);
    String time = timeTravelCalculator.getTravelTime("Leeds", "Manchester");
    assertEquals("00:00", time, "Should return zero '00:00' ");
  }
}
