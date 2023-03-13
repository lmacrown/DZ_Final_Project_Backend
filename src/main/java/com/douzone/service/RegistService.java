package com.douzone.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.DAO.InputDAO;
import com.douzone.DAO.RegistDAO;
import com.douzone.entity.EarnerVO;
import com.douzone.entity.IncomingVO;
import com.douzone.entity.TaxInfoVO;

@Service
public class RegistService {

	@Autowired
	RegistDAO registDAO;

	public List<IncomingVO> search_earner_code(HashMap<String, Object> map) {
		return registDAO.search_earner_code(map);
	}

	public List<IncomingVO> search_div_code(HashMap<String, Object> map) {
		return registDAO.search_div_code(map);
	}
	
}