package com.emarsys.homeworkproject.services;

import com.emarsys.homeworkproject.exceptions.NotWorkingDayException;
import com.emarsys.homeworkproject.models.DayType;
import com.emarsys.homeworkproject.models.TimeAndDueDTO;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class DeadlineService {

  public String calculateDueTime(TimeAndDueDTO dto) throws NotWorkingDayException {
    Calendar calendar = getCalendar(dto.getDate());
    if (!isBetweenWorkingHours(getDayByDayType(getDayType(calendar.getTime())), calendar.get(Calendar.HOUR_OF_DAY))) {
      throw new NotWorkingDayException(
          "The submission has been made on a non-working day. Please submit on a working day!");
    } else {
      int submitHour = calendar.get(Calendar.HOUR_OF_DAY);
      Calendar dueCalendar = createDueCalendar(dto, submitHour);
      String dueDay = getDayByDayType(getDayType(dueCalendar.getTime()));
      int dueHour = dueCalendar.get(Calendar.HOUR_OF_DAY);
      int dueMinute = dueCalendar.get(Calendar.MINUTE);
      return createResponse(dueDay, dueHour, dueMinute);
    }
  }

  private String createTwoDigitFormat(int number) {
    return String.format("%02d", number);
  }

  private String createResponse(String dueDay, int dueHour, int dueMinute) {
    return "The due time for submission is: " + dueDay + ", " + createTwoDigitFormat(dueHour) + ":" +
        createTwoDigitFormat(dueMinute);
  }

  private Calendar createDueCalendar(TimeAndDueDTO dto, int submitHour) {
    Calendar dueCalendar = getCalendar(dto.getDate());
    if ((submitHour + calculateDaysAndHours(dto.getTurnAroundHours())[1]) >= 17) {
      dueCalendar.add(Calendar.DATE, calculateDaysAndHours(dto.getTurnAroundHours())[0]);
      dueCalendar.set(Calendar.HOUR_OF_DAY,
          (9 + (submitHour + (calculateDaysAndHours(dto.getTurnAroundHours())[1]) - 17)));
    } else {
      dueCalendar.add(Calendar.DATE, calculateDaysAndHours(dto.getTurnAroundHours())[0]);
      dueCalendar.add(Calendar.HOUR_OF_DAY, calculateDaysAndHours(dto.getTurnAroundHours())[1]);
    }
    return dueCalendar;
  }

  private int[] calculateDaysAndHours(Integer turnOverHours) {
    int[] daysAndHours = new int[2];
    int days = turnOverHours / 8;
    daysAndHours[0] = days;
    int hours = turnOverHours % 8;
    daysAndHours[1] = hours;
    return daysAndHours;
  }

  private boolean isBetweenWorkingHours(String day, Integer hour) {
    return !day.equals("Saturday") && !day.equals("Sunday") && hour >= 9 && hour < 17;
  }

  private DayType getDayType(Date date) {
    Calendar calendar = getCalendar(date);
    return getDayTypeByInt(calendar.get(Calendar.DAY_OF_WEEK));
  }

  private Calendar getCalendar(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar;
  }

  private DayType getDayTypeByInt(Integer dayOfWeek) {
    if (dayOfWeek != 1 && dayOfWeek != 2 && dayOfWeek != 3 && dayOfWeek != 4 && dayOfWeek != 5 && dayOfWeek != 6 &&
        dayOfWeek != 7) {
      return null;
    } else if (dayOfWeek % 7 == 1) {
      return DayType.MONDAY;
    } else if (dayOfWeek % 7 == 2) {
      return DayType.TUESDAY;
    } else if (dayOfWeek % 7 == 3) {
      return DayType.WEDNESDAY;
    } else if (dayOfWeek % 7 == 4) {
      return DayType.THURSDAY;
    } else if (dayOfWeek % 7 == 5) {
      return DayType.FRIDAY;
    } else if (dayOfWeek % 7 == 6) {
      return DayType.SATURDAY;
    } else {
      return DayType.SUNDAY;
    }
  }

  private String getDayByDayType(DayType dayType) {
    return dayType.toString();
  }
}
