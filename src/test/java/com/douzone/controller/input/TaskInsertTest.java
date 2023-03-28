package com.douzone.controller.input;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.douzone.entity.input.TaskInsertVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskInsertTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testTaskInsertNormal() throws Exception {
        TaskInsertVO taskInsertVO = TaskInsertVO.builder()
                .worker_id("yuchan2")
                .payment_ym(202201)
                .earner_code("723456")
                .build();

        mockMvc.perform(post("/input/task_insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(taskInsertVO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testTaskInsertMissingWorkerId() throws Exception {
        TaskInsertVO taskInsertVO = TaskInsertVO.builder()
                .payment_ym(202201)
                .earner_code("123456")
                .build();

        mockMvc.perform(post("/input/task_insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(taskInsertVO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testTaskInsertInvalidPaymentYm() throws Exception {
        TaskInsertVO taskInsertVO = TaskInsertVO.builder()
                .worker_id("yuchan2")
                .payment_ym(199999)
                .earner_code("123456")
                .build();

        mockMvc.perform(post("/input/task_insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(taskInsertVO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testTaskInsertOutOfRangePaymentYm() throws Exception {
        TaskInsertVO taskInsertVO = TaskInsertVO.builder()
                .worker_id("yuchan2")
                .payment_ym(300000)
                .earner_code("123456")
                .build();

        mockMvc.perform(post("/input/task_insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(taskInsertVO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testTaskInsertInvalidEarnerCode() throws Exception {
        TaskInsertVO taskInsertVO = TaskInsertVO.builder()
                .worker_id("yuchan2")
                .payment_ym(202201)
                .earner_code("12345a")
                .build();

        mockMvc.perform(post("/input/task_insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(taskInsertVO)))
                .andExpect(status().isBadRequest());
    }
}
