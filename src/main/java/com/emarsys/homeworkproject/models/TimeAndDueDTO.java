package com.emarsys.homeworkproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class TimeAndDueDTO {
  Date date;
  Integer turnAroundHours;
}
