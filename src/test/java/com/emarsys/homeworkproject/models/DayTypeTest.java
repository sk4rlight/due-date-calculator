package com.emarsys.homeworkproject.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DayTypeTest {

  @Test
  public void testEnums() {
    assertEquals("Monday", DayType.MONDAY.toString());
    assertEquals("Tuesday", DayType.TUESDAY.toString());
    assertEquals("Wednesday", DayType.WEDNESDAY.toString());
    assertEquals("Thursday", DayType.THURSDAY.toString());
    assertEquals("Friday", DayType.FRIDAY.toString());
    assertEquals("Saturday", DayType.SATURDAY.toString());
    assertEquals("Sunday", DayType.SUNDAY.toString());
  }
}