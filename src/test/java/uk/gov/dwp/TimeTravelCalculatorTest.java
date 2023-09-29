package uk.gov.dwp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TimeTravelCalculatorTest {

  @Test
  @DisplayName("Given that the same value as travelFrom and travelTo then return '00:00'")
  public void givenSameToAndFromReturnZeroHourAndMinute(){
    TimeTravelCalculator timeTravelCalculator = new TimeTravelCalculator();
    String time = timeTravelCalculator.getTravelTime("", "");
    assertEquals("00:00",  time, "Should return zero");
  }
}
