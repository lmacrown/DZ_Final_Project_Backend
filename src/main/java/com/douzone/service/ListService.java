package com.douzone.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.Aes;
import com.douzone.dao.ListDAO;

@Service("listService")
public class ListService {

	@Autowired
	ListDAO listDAO;

	public List<Map<String, Object>> search_earner_code(Map<String, Object> map) throws Exception {
		List<Map<String, Object>> search_earner = listDAO.search_earner_code(map);
		
		EncodingService.decrypt_list(search_earner);
		
		return search_earner;
	}

	public List<Map<String, Object>> search_div_code(Map<String, Object> map) throws Exception {
		List<Map<String, Object>> search_div_code = listDAO.search_div_code(map);
		
		EncodingService.decrypt_list(search_div_code);
		
		return search_div_code;
	}
	
}