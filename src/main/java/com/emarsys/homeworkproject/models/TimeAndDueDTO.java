package com.emarsys.homeworkproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class TimeAndDueDTO {
  Date date;
  Integer turnAroundHours;
}
