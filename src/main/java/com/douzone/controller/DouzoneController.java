package com.douzone.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.service.DouzoneService;

@RestController
@CrossOrigin("*")
public class DouzoneController {

	@Autowired
	DouzoneService douzoneService;

	// 사업소득자 등록
	@GetMapping(value = "/earner_list")
	public Map<String, Object> earner_list(String worker_id) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("earner_list", douzoneService.earner_list(worker_id));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		result.put("status_code", false);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@GetMapping(value = "/get_earner")
	public Map<String, Object> get_earner(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();
		result.put("earner_info", douzoneService.get_earner(params));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@PostMapping(value = "/earner_insert")
	public Map<String, Object> earner_insert(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		douzoneService.earner_insert(params);
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@PatchMapping(value = "/earner_update")
	public Map<String, Object> earner_update(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		douzoneService.earner_update(params);
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@GetMapping(value = "/list_divcode")
	public Map<String, Object> list_divcode() {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("div_list", douzoneService.list_divcode());
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@DeleteMapping(value = "/earner_delete")
	public Map<String, Object> earner_delete(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		douzoneService.earner_delete(params);
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	// 사업소득자 자료입력
	
	@GetMapping(value = "/earner_search")
	public Map<String, Object> earner_search(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("earner_list", douzoneService.earner_search(params));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);
		
		return result;
	}

	@GetMapping(value = "/get_earners")
	public Map<String, Object> get_earners(String worker_id, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("earner_list", douzoneService.get_earners(worker_id, session));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);
		
		return result;
	}

	@PutMapping(value = "/put_task")
	public Map<String, Object> put_task(@RequestBody HashMap<String, Object> params, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		douzoneService.put_task(params,session);
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);
		
		return result;
	}

	@DeleteMapping(value = "/delete_task")
	public Map<String, Object> delete_task(@RequestBody HashMap<String, Object> params, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		douzoneService.delete_task(params,session);
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);
		
		return result;

	}

	@PostMapping(value = "/tax_insert")
	public Map<String, Object> tax_insert(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		//result.put("div_list", douzoneService.list_divcode());
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);
		
		return result;
	}

	@PatchMapping(value = "/tax_update")
	public Map<String, Object> tax_update(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		//result.put("div_list", douzoneService.list_divcode());
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);
		
		return result;

	}

	@GetMapping(value = "/get_tax")
	public Map<String, Object> get_tax(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		//result.put("div_list", douzoneService.list_divcode());
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);
		
		return result;

	}

}
