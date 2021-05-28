package com.emarsys.homeworkproject.services;

import com.emarsys.homeworkproject.exceptions.InvalidDayException;
import com.emarsys.homeworkproject.models.DayType;
import com.emarsys.homeworkproject.models.TimeAndDueDTO;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class DeadlineService {

  public String CalculateDueTime(TimeAndDueDTO dto) throws InvalidDayException {
    Calendar calendar = getCalendar(dto.getDate());
    String submitDay = getDayByDayType(getDayType(dto.getDate()));
    Integer submitHour = calendar.get(Calendar.HOUR_OF_DAY);
    Integer submitMinute = calendar.get(Calendar.MINUTE);

    return new String();
  }

  private boolean isBetweenWorkingHours(String day, Integer hour){
    return !day.equals("Saturday") && !day.equals("Sunday") && hour >= 9 && hour < 17;
  }

  private DayType getDayType(Date date) throws InvalidDayException {
    Calendar calendar = getCalendar(date);
    return getDayTypeByInt(calendar.get(Calendar.DAY_OF_WEEK));
  }

  private Calendar getCalendar(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar;
  }

  private DayType getDayTypeByInt(Integer dayOfWeek) throws InvalidDayException {
    if(dayOfWeek != 1 && dayOfWeek != 2 && dayOfWeek != 3 && dayOfWeek != 4 && dayOfWeek != 5 && dayOfWeek != 6 && dayOfWeek != 7){
      return null;
    } else if (dayOfWeek == 1){
      return DayType.MONDAY;
    } else if (dayOfWeek == 2) {
      return DayType.TUESDAY;
    } else if (dayOfWeek == 3) {
      return DayType.WEDNESDAY;
    } else if (dayOfWeek == 4) {
      return DayType.THURSDAY;
    } else if (dayOfWeek == 5) {
      return DayType.FRIDAY;
    } else if (dayOfWeek == 6) {
      return DayType.SATURDAY;
    } else {
      return DayType.SUNDAY;
    }
  }

  private String getDayByDayType(DayType dayType) {
    return dayType.toString();
  }
}
