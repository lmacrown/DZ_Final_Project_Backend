package com.douzone.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.DAO.InputDAO;
import com.douzone.entity.TaxInfoVO;

@Service
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
		//get_tax조회시 inner join 한 이유
		return inputDAO.get_tax(params);
	}

	public  List<TaxInfoVO> put_tax(HashMap<String, Object> params) {
		boolean is_exist = true;
		if (null==params.get("tax_id")) is_exist = false;
		if (!is_exist) 
			inputDAO.tax_insert(params);
		else {
			update_tax(params);
		}
		return get_tax(params);
	}
	
	public  void update_tax(HashMap<String, Object> params) {
		TaxInfoVO taxInfo = new TaxInfoVO();

		inputDAO.tax_update(params);
		inputDAO.tax_backup(taxInfo);
	}

}