package com.douzone.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.handler.GlobalResponseHandler;
import com.douzone.service.InputService;

@RestController("inputController")
@CrossOrigin("*")
public class InputController {

	@Autowired
	InputService inputService;
	// 사업소득자 자료입력
	@Autowired
	GlobalResponseHandler gloabalResponseHandler;

	@PostMapping(value = "/input/earner_search")
	public Map<String, Object> earner_search(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		result.put("earner_list", inputService.earner_search(params));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@PostMapping(value = "/input/get_earners")
	public Map<String, Object> get_earners(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		result.put("earner_list", inputService.get_earners(params));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@PostMapping(value = "/input/get_tax")
	public Map<String, Object> get_tax(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		result.put("tax_list", inputService.get_tax(params));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);

	}

	@PostMapping(value = "/input/tax_insert")
	public Map<String, Object> tax_insert(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		result.put("tax_id", inputService.tax_insert(params));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}
	
	@PatchMapping(value = "/input/update_taxdate")
	public Map<String, Object> update_taxdate(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		inputService.update_taxdate(params);
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}
	
	@PatchMapping(value = "/input/update_taxinfo")
	public Map<String, Object> update_taxinfo(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		result.put("tax_id", inputService.update_taxinfo(params));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}
}