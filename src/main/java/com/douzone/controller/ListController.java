package com.douzone.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.handler.GlobalResponseHandler;
import com.douzone.service.ListService;


@RestController("listController")
@CrossOrigin("*")
public class ListController {

	@Autowired
	ListService listService;
	@Autowired
	GlobalResponseHandler gloabalResponseHandler;
	
	// 소득자별조회
	@PostMapping(value = "/list/search_earner_code")
	public Map<String, Object> search_earner_code( @RequestBody HashMap<String, Object> map) throws Exception {
		Map<String, Object> result = new HashMap<>();
		List<Map<String, Object>> incoming = listService.search_earner_code(map);//VO->Map으로 리턴
		result.put("earnerInfo", incoming);

		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	// 소득구분별조회
	@PostMapping(value = "/list/search_div_code")
	public Map<String, Object> search_div_code(@RequestBody HashMap<String, Object> map) throws Exception {
		Map<String, Object> result = new HashMap<>();
		List<Map<String, Object>> incoming = listService.search_div_code(map);
		result.put("earnerInfo", incoming);

		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

}