package com.douzone.controller;



import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.service.InputService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
public class InputController {

	@Autowired
	InputService inputService;
	// 사업소득자 자료입력

	@GetMapping(value = "/input/earner_search")
	public Map<String, Object> earner_search(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("earner_list", inputService.earner_search(params));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@GetMapping(value = "/get_earners")
	public Map<String, Object> get_earners(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();
	
		result.put("earner_list", inputService.get_earners(params));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@GetMapping(value = "/get_tax")
	public Map<String, Object> get_tax(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("tax_list", inputService.get_tax(params));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;

	}

	@PutMapping(value = "/put_tax")
	public Map<String, Object> put_tax(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("tax_info", inputService.put_tax(params));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	

}