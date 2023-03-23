package com.douzone.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.dao.ListDAO;

@Service("listService")
public class ListService {

	@Autowired
	ListDAO listDAO;

	public List<Map<String, Object>> search_earner_code(Map<String, Object> map) {
		return listDAO.search_earner_code(map);
	}

	public List<Map<String, Object>> search_div_code(Map<String, Object> map) {
		return listDAO.search_div_code(map);
	}
	
}