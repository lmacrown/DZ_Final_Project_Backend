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

import com.douzone.entity.input.GetTaxVO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

//imports 생략

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class GetTaxTest {
 @Autowired
 private MockMvc mockMvc;

 @Test
 public void testGetTaxNormal() throws Exception {
     GetTaxVO getTaxVO = GetTaxVO.builder()
             .earner_code("123456")
             .worker_id("yuchan2")
             .payment_ym(202201)
             .build();

     mockMvc.perform(post("/input/get_tax")
             .contentType(MediaType.APPLICATION_JSON)
             .content(new ObjectMapper().writeValueAsString(getTaxVO)))
             .andExpect(status().isOk())
             .andExpect(jsonPath("$.tax_list").exists());
 }

 @Test
 public void testGetTaxMissingEarnerCode() throws Exception {
     GetTaxVO getTaxVO = GetTaxVO.builder()
             .worker_id("yuchan2")
             .payment_ym(202201)
             .build();

     mockMvc.perform(post("/input/get_tax")
             .contentType(MediaType.APPLICATION_JSON)
             .content(new ObjectMapper().writeValueAsString(getTaxVO)))
             .andExpect(status().isBadRequest());
 }

 @Test
 public void testGetTaxInvalidEarnerCode() throws Exception {
     GetTaxVO getTaxVO = GetTaxVO.builder()
             .earner_code("12345a")
             .worker_id("yuchan2")
             .payment_ym(202201)
             .build();

     mockMvc.perform(post("/input/get_tax")
             .contentType(MediaType.APPLICATION_JSON)
             .content(new ObjectMapper().writeValueAsString(getTaxVO)))
             .andExpect(status().isBadRequest());
 }

 @Test
 public void testGetTaxMissingWorkerId() throws Exception {
     GetTaxVO getTaxVO = GetTaxVO.builder()
             .earner_code("123456")
             .payment_ym(202201)
             .build();

     mockMvc.perform(post("/input/get_tax")
             .contentType(MediaType.APPLICATION_JSON)
             .content(new ObjectMapper().writeValueAsString(getTaxVO)))
             .andExpect(status().isBadRequest());
 }

 @Test
 public void testGetTaxInvalidPaymentYm() throws Exception {
     GetTaxVO getTaxVO = GetTaxVO.builder()
             .earner_code("123456")
             .worker_id("yuchan2")
             .payment_ym(199999)
             .build();

     mockMvc.perform(post("/input/get_tax")
             .contentType(MediaType.APPLICATION_JSON)
             .content(new ObjectMapper().writeValueAsString(getTaxVO)))
             .andExpect(status().isBadRequest());
 }

 @Test
 public void testGetTaxOutOfRangePaymentYm() throws Exception {
     GetTaxVO getTaxVO = GetTaxVO.builder()
             .earner_code("123456")
             .worker_id("yuchan2")
             .payment_ym(300000)
             .build();

     mockMvc.perform(post("/input/get_tax")
             .contentType(MediaType.APPLICATION_JSON)
             .content(new ObjectMapper().writeValueAsString(getTaxVO)))
             .andExpect(status().isBadRequest());
 }
}
