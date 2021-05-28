package com.emarsys.homeworkproject.controllers;

import com.emarsys.homeworkproject.exceptions.NotWorkingDayException;
import com.emarsys.homeworkproject.models.TimeAndDueDTO;
import com.emarsys.homeworkproject.services.DeadlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeadlineController {

  DeadlineService deadlineService;

  @Autowired
  public DeadlineController(DeadlineService deadlineService) {
    this.deadlineService = deadlineService;
  }

  @PostMapping("/due-date")
  public ResponseEntity<String> calculateDueTime(@RequestBody(required = false) TimeAndDueDTO dto)
      throws NotWorkingDayException {
    return ResponseEntity.ok(deadlineService.calculateDueTime(dto));

  }
}
