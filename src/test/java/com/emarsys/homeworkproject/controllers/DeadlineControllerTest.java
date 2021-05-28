package com.emarsys.homeworkproject.controllers;

import com.emarsys.homeworkproject.services.DeadlineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DeadlineController.class)
@AutoConfigureMockMvc
public class DeadlineControllerTest {
  @Autowired
  MockMvc mockMvc;
  @MockBean
  DeadlineService deadlineService;

  @Test
  public void calculateDueTime_HappyCase() throws Exception {

    String mockString = "mockString";
    when(deadlineService.calculateDueTime(any())).thenReturn(mockString);

    mockMvc.perform(post("/due-date")
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("utf-8"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").exists());
  }
}