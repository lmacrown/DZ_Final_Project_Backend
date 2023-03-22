package com.douzone.controller;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.douzone.service.RegistService;



@WebMvcTest(controllers = RegistController.class)
public class RegistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistService registService;

    @Test
    public void get_countTest() throws Exception {
        // Given
        HashMap<String, Object> params = new HashMap<>();
        params.put("worker_id", "yuchan2");
        params.put("code_count", 52);
        Map<String, Object> expectedResult = new HashMap<>();
        expectedResult.put("code_count", "42");

        when(registService.get_count(params)).thenReturn("42");

        // When & Then
        mockMvc.perform(post("/regist/get_count")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"key\":\"value\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("code_count").value("42"));

        verify(registService, times(1)).get_count(params);
    }


	
//	@PostMapping(value = "/regist/check_code")
//	public Map<String, Object> check_code(@RequestBody HashMap<String, Object> params) {
//		Map<String, Object> result = new HashMap<>();
//		result.put("code_count", registService.check_code(params));
//		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
//	}
//
//	@GetMapping(value = "/regist/earner_list/{worker_id}")
//	public Map<String, Object> earner_list(Model model,@PathVariable String worker_id) {
//		Map<String, Object> result = new HashMap<>();
//		result.put("earner_list", registService.earner_list(worker_id));
//		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
//	}
//	
//	@PostMapping(value = "/regist/get_earner")
//	public Map<String, Object> get_earner(@RequestBody HashMap<String, Object> params) {
//		Map<String, Object> result = new HashMap<>();
//		result.put("earner_info", registService.get_earner(params));
//		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
//	}
//
//	@PostMapping(value = "/regist/earner_insert")
//	public Map<String, Object> earner_insert(@RequestBody HashMap<String, Object> params) {
//		Map<String, Object> result = new HashMap<>();
//		result.put("code_count", registService.earner_insert(params));
//		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
//	}
//
//	@PatchMapping(value = "/regist/earner_update")
//	public Map<String, Object> earner_update(@RequestBody HashMap<String, Object> params) {
//		Map<String, Object> result = new HashMap<>();
//		registService.earner_update(params);
//		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
//	}
//
//	@GetMapping(value = "/regist/list_divcode")
//	public Map<String, Object> list_divcode() {
//		Map<String, Object> result = new HashMap<>();
//		result.put("div_list", registService.list_divcode());
//		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
//	}
//
//	@DeleteMapping(value = "/regist/earner_delete")
//	public Map<String, Object> earner_delete(@RequestBody HashMap<String, Object> params) {
//		Map<String, Object> result = new HashMap<>();
//		registService.earner_delete(params);
//		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
//	}
}