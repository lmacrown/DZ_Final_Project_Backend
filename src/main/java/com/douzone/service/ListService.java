package com.douzone.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.dao.ListDAO;
import com.douzone.entity.IncomingVO;

@Service("listService")
public class ListService {

	@Autowired
	ListDAO listDAO;

	public List<IncomingVO> search_earner_code(HashMap<String, Object> map) {
		return listDAO.search_earner_code(map);
	}

	public List<IncomingVO> search_div_code(HashMap<String, Object> map) {
		return listDAO.search_div_code(map);
	}
	
}