package com.douzone.controller.input;

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
public class EarnerSearchTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSearchEarnerNormal() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("worker_id", "yuchan2");
        params.put("search_value", "testValue");
        log.info("start Normal");
        mockMvc.perform(post("/input/earner_search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(params)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.earner_list").exists());
    }
    @Test
    public void testSearchEarnerEmptySearchValue() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("worker_id", "yuchan2");
        params.put("search_value", "");
        log.info("start EmptySearchValue");
        mockMvc.perform(post("/input/earner_search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(params)))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.earner_list").exists());
    }

    @Test
    public void testSearchEarnerMissingWorkerId() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("search_value", "testValue");
        log.info("start MissingWorkerId");
        mockMvc.perform(post("/input/earner_search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(params)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSearchEarnerMissingSearchValue() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("worker_id", "yuchan2");
        log.info("start MissingSearchValue");
        mockMvc.perform(post("/input/earner_search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(params)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSearchEarnerEmptyWorkerId() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("worker_id", "");
        params.put("search_value", "testValue");
        log.info("start EmptyWorkerId");
        mockMvc.perform(post("/input/earner_search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(params)))
                .andExpect(status().isBadRequest());
    }
}
