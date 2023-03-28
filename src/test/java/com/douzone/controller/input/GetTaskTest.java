package com.douzone.controller.input;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.douzone.entity.input.GetTaskVO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class GetTaskTest {
 @Autowired
 private MockMvc mockMvc;

 @Test
 public void testGetTaskNormal() throws Exception {
     GetTaskVO getTaskVO = GetTaskVO.builder()
             .worker_id("yuchan2")
             .payment_ym(202205)
             .build();

     mockMvc.perform(post("/input/get_task")
             .contentType(MediaType.APPLICATION_JSON)
             .content(new ObjectMapper().writeValueAsString(getTaskVO)))
             .andExpect(status().isOk())
             .andExpect(jsonPath("$.task_list").exists());
 }

 @Test
 public void testGetTaskMissingWorkerId() throws Exception {
     GetTaskVO getTaskVO = GetTaskVO.builder()
             .payment_ym(202201)
             .build();

     mockMvc.perform(post("/input/get_task")
             .contentType(MediaType.APPLICATION_JSON)
             .content(new ObjectMapper().writeValueAsString(getTaskVO)))
             .andExpect(status().isBadRequest());
 }

 @Test
 public void testGetTaskInvalidPaymentYm() throws Exception {
     GetTaskVO getTaskVO = GetTaskVO.builder()
             .worker_id("yuchan2")
             .payment_ym(199999)
             .build();

     mockMvc.perform(post("/input/get_task")
             .contentType(MediaType.APPLICATION_JSON)
             .content(new ObjectMapper().writeValueAsString(getTaskVO)))
             .andExpect(status().isBadRequest());
 }

 @Test
 public void testGetTaskOutOfRangePaymentYm() throws Exception {
     GetTaskVO getTaskVO = GetTaskVO.builder()
             .worker_id("yuchan2")
             .payment_ym(300000)
             .build();

     mockMvc.perform(post("/input/get_task")
             .contentType(MediaType.APPLICATION_JSON)
             .content(new ObjectMapper().writeValueAsString(getTaskVO)))
             .andExpect(status().isBadRequest());
 }
}
