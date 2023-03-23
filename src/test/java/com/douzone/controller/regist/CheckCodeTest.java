package com.douzone.controller.regist;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class CheckCodeTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCheckCodeNormal() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("worker_id", "yuchan2");
        params.put("custom_code", "123123");
        log.info("start Nomal");
        mockMvc.perform(post("/regist/check_code")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(params)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code_count").exists());
    }
    
    @Test
    public void testCheckCodeExist() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("worker_id", "yuchan2");
        params.put("custom_code", "000001");
        log.info("start Exist");
        mockMvc.perform(post("/regist/check_code")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(params)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code_count").exists());
    }
    
    
    @Test
    public void testCheckCodeMissingParameter() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("worker_id", "yuchan2");
        log.info("start MissingParam");
        mockMvc.perform(post("/regist/check_code")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(params)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void testCheckCodeRangeOverflow() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("worker_id", "yuchan2");
        params.put("custom_code", "000000000000000222");
        log.info("start overflow");
        mockMvc.perform(post("/regist/check_code")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(params)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void testCheckCodeWrongType() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("worker_id", "yuchan2");
        params.put("custom_code", 1234);
        log.info("start WorngType");
        mockMvc.perform(post("/regist/check_code")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(params)))
                .andExpect(status().isBadRequest());
    }
}