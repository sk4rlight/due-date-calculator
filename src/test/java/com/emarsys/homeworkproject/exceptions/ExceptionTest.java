package com.emarsys.homeworkproject.exceptions;

import com.emarsys.homeworkproject.models.TimeAndDueDTO;
import com.emarsys.homeworkproject.services.DeadlineService;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ExceptionTest {

  private DeadlineService mockDeadlineService;

  @BeforeEach
  public void setUp() {

    mockDeadlineService = Mockito.mock(DeadlineService.class);

    MockitoAnnotations.openMocks(this);
  }

  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Test
  public void notWorkingDayException_HappyCase() throws Exception {
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
    df.setTimeZone(TimeZone.getTimeZone("UTC"));

    Date mockDate = df.parse("22-05-2021 03:00");
    Integer mockTurnAroundHours = 3;
    TimeAndDueDTO mockDTO = new TimeAndDueDTO(mockDate, mockTurnAroundHours);

    exceptionRule.expect(NotWorkingDayException.class);
    exceptionRule.expectMessage("The submission has been made on a non-working day. Please submit on a working day!");

    mockDeadlineService.calculateDueTime(mockDTO);
  }
}
