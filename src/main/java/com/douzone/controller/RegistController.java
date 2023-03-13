package com.douzone.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.service.RegistService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
public class RegistController {

	@Autowired
	RegistService registService;

	//사업자 등록
	@GetMapping(value = "/get_count")
	public Map<String, Object> get_count(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("code_count", registService.get_count(params));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@GetMapping(value = "/check_code")
	public Map<String, Object> check_code(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("status_code", !registService.check_code(params));
		result.put("time_stamp", time_stamp);

		return result;
	}

	@GetMapping(value = "/earner_list")
	public Map<String, Object> earner_list(String worker_id) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("earner_list", registService.earner_list(worker_id));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@GetMapping(value = "/get_earner")
	public Map<String, Object> get_earner(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();
		result.put("earner_info", registService.get_earner(params));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@PostMapping(value = "/earner_insert")
	public Map<String, Object> earner_insert(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("code_count", registService.earner_insert(params));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@PatchMapping(value = "/earner_update")
	public Map<String, Object> earner_update(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		registService.earner_update(params);
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@GetMapping(value = "/list_divcode")
	public Map<String, Object> list_divcode() {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();
		System.out.println("됨?");
		result.put("div_list", registService.list_divcode());
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);
		System.out.println("됨!");
		return result;
	}

	@DeleteMapping(value = "/earner_delete")
	public Map<String, Object> earner_delete(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		registService.earner_delete(params);
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}
}