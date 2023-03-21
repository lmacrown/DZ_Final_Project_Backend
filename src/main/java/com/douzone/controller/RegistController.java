package com.douzone.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.handler.GlobalResponseHandler;
import com.douzone.service.RegistService;


@RestController("registController")
@CrossOrigin("*")
public class RegistController {

	@Autowired
	RegistService registService;
	
	@Autowired
	GlobalResponseHandler gloabalResponseHandler;
	
	@PostMapping(value = "/regist/get_count")
	public Map<String, Object> get_count(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		result.put("code_count", registService.get_count(params));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@PostMapping(value = "/regist/check_code")
	public Map<String, Object> check_code(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		result.put("code_count", registService.check_code(params));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@GetMapping(value = "/regist/earner_list/{worker_id}")
	public Map<String, Object> earner_list(Model model,@PathVariable String worker_id) {
		Map<String, Object> result = new HashMap<>();
		result.put("earner_list", registService.earner_list(worker_id));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@PostMapping(value = "/regist/get_earner")
	public Map<String, Object> get_earner(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		result.put("earner_info", registService.get_earner(params));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@PostMapping(value = "/regist/earner_insert")
	public Map<String, Object> earner_insert(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		result.put("code_count", registService.earner_insert(params));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@PatchMapping(value = "/regist/earner_update")
	public Map<String, Object> earner_update(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		registService.earner_update(params);
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@GetMapping(value = "/regist/list_divcode")
	public Map<String, Object> list_divcode() {
		Map<String, Object> result = new HashMap<>();
		result.put("div_list", registService.list_divcode());
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/regist/earner_delete")
	public Map<String, Object> earner_delete(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		registService.earner_delete(params);
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}
}