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
public class GetCountTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCountNormal() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("worker_id", "yuchan2");
        params.put("code_count", 70);
        log.info("start Nomal");
        mockMvc.perform(post("/regist/get_count")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(params)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code_count").exists());
    }

    @Test
    public void testGetCountMissingParameter() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("worker_id", "yuchan2");
        log.info("start MissingParam");
        mockMvc.perform(post("/regist/get_count")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(params)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void testGetCountWhenCodeCountIsNegative() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("worker_id", "yuchan2");
        params.put("code_count",-1341);
        log.info("start Negative");
        mockMvc.perform(post("/regist/get_count")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(params)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void testGetCountWhenCodeCountIsZero() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("worker_id", "yuchan2");
        params.put("code_count",0);
        log.info("start Zero");
        mockMvc.perform(post("/regist/get_count")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(params)))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testGetCountWrongType() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("worker_id", "yuchan2");
        params.put("code_count", "hi");
        log.info("start WorngType");
        mockMvc.perform(post("/regist/get_count")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(params)))
                .andExpect(status().isBadRequest());
    }
}