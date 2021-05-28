package com.emarsys.homeworkproject.services;

import com.emarsys.homeworkproject.exceptions.NotWorkingDayException;
import com.emarsys.homeworkproject.models.TimeAndDueDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

public class DeadlineServiceTest {

  @Autowired
  MockMvc mockMvc;
  @MockBean
  DeadlineService deadlineService;

  @Before
  public void setUp() {
    this.deadlineService = new DeadlineService();

  }

  @Test(expected = NotWorkingDayException.class)
  public void calculateDueTime_whenNotWorkingDay_thenThrowException() throws Exception {

    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
    df.setTimeZone(TimeZone.getTimeZone("UTC"));

    Date mockDate = df.parse("22-05-2021 03:00");
    Integer mockTurnAroundHours = 3;
    TimeAndDueDTO mockDTO = new TimeAndDueDTO(mockDate, mockTurnAroundHours);

    deadlineService.calculateDueTime(mockDTO);
  }

  @Test
  public void calculateDueTime_whenOnWorkingDayWithNoHourOverflow_thenHappyCase() throws Exception {

    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
    df.setTimeZone(TimeZone.getTimeZone("Europe/Budapest"));

    Date mockDate = df.parse("25-05-2021 10:00");
    Integer mockTurnAroundHours = 10;
    TimeAndDueDTO mockDTO = new TimeAndDueDTO(mockDate, mockTurnAroundHours);

    assertEquals("The due time for submission is: Tuesday, 12:00", deadlineService.calculateDueTime(mockDTO));
  }

  @Test
  public void calculateDueTime_whenOnWorkingDayWithHourOverflow_thenHappyCase() throws Exception {

    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
    df.setTimeZone(TimeZone.getTimeZone("Europe/Budapest"));

    Date mockDate = df.parse("25-05-2021 16:00");
    Integer mockTurnAroundHours = 2;
    TimeAndDueDTO mockDTO = new TimeAndDueDTO(mockDate, mockTurnAroundHours);

    assertEquals("The due time for submission is: Wednesday, 10:00", deadlineService.calculateDueTime(mockDTO));
  }

}