package com.emarsys.homeworkproject.controllers;

import com.emarsys.homeworkproject.exceptions.InvalidDayException;
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
  public ResponseEntity<?> calculateDueTime(@RequestBody TimeAndDueDTO dto) throws InvalidDayException {
    return ResponseEntity.ok(deadlineService.CalculateDueTime(dto));

  }
}
