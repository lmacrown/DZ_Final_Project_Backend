package com.douzone.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.service.RegistService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController("registController")
@CrossOrigin("*")
public class RegistController {

	@Autowired
	RegistService registService;

	//사업자 등록
	@GetMapping(value = "/regist/get_count")
	public Map<String, Object> get_count(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();
		
		log.info("Received a request to get user with ID: {}", params);
		result.put("code_count", registService.get_count(params));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@GetMapping(value = "/regist/check_code")
	public Map<String, Object> check_code(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("status_code", !registService.check_code(params));
		result.put("time_stamp", time_stamp);

		return result;
	}

	@GetMapping(value = "/regist/earner_list/{worker_id}")
	public Map<String, Object> earner_list(Model model,@PathVariable String worker_id) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();
        
       
        log.info("hi");
		result.put("earner_list", registService.earner_list(worker_id));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@GetMapping(value = "/resgist/get_earner")
	public Map<String, Object> get_earner(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();
		result.put("earner_info", registService.get_earner(params));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@PostMapping(value = "/resgist/earner_insert")
	public Map<String, Object> earner_insert(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("code_count", registService.earner_insert(params));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@PatchMapping(value = "/regist/earner_update")
	public Map<String, Object> earner_update(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		registService.earner_update(params);
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@GetMapping(value = "/regist/list_divcode")
	public Map<String, Object> list_divcode() {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("div_list", registService.list_divcode());
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@DeleteMapping(value = "/regist/earner_delete")
	public Map<String, Object> earner_delete(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();
		
		registService.earner_delete(params);
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}
}