package com.douzone.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.dao.InputDAO;
import com.douzone.entity.TaxInfoVO;



@Service("inputService")
public class InputService {

	@Autowired
	InputDAO inputDAO;

	public List<Map<String, Object>> earner_search(Map<String, Object> params) {
		return inputDAO.earner_search(params);
	}

	public List<Map<String, Object>> get_earners(HashMap<String, Object> params) {
		return inputDAO.get_earners(params);
	}

	public List<TaxInfoVO> get_tax(HashMap<String, Object> params) {
		return inputDAO.get_tax(params);
	}

	public int put_tax(HashMap<String, Object> params) {
		if (null == params.get("tax_id"))
			inputDAO.tax_insert(params);
		else
			inputDAO.tax_update(params);
		
		return (int) params.get("tax_id");
	}

	public TaxInfoVO get_tax_one(HashMap<String, Object> params) {
		
		return (TaxInfoVO) inputDAO.get_tax_one(params);
	}

}