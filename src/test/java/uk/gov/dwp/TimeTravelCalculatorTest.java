package uk.gov.dwp;

import org.junit.jupiter.api.Test;

public class TimeTravelCalculatorTest {

  @Test
  public void createClass(){
    TimeTravelCalculator timeTravelCalculator = new TimeTravelCalculator();
    String time = timeTravelCalculator.getTravelTime("", "");
  }
}
