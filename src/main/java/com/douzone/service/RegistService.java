package com.douzone.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.dao.RegistDAO;
import com.douzone.entity.EarnerVO;

@Service("registService")
public class RegistService {

	@Autowired
	RegistDAO registDAO;

	public List<Map<String, Object>> list_divcode() {
		return registDAO.list_divcode();
	}

	public List<Map<String, Object>> earner_list(String worker_id) {
		return registDAO.earner_list(worker_id);
	}

	public EarnerVO get_earner(Map<String, Object> params) {
		return registDAO.get_earner(params);
	}

	public int earner_insert(Map<String, Object> params) {
		earner_form_insert(params);
		if ((boolean) params.get("is_default")) {
			update_count(params);
			return Integer.parseInt((String) params.get("earner_code"));
		}
		return 0;
	}
	
	private void update_count(Map<String, Object> params) {
		registDAO.update_count(params);
	}
	
	public void earner_form_insert(Map<String, Object> params) {
		registDAO.earner_form_insert(params);
	}

	public void earner_update(Map<String, Object> params) {
		registDAO.earner_update(params);
	}

	public void earner_delete(Map<String, Object> params) {
		registDAO.earner_delete(params);
	}

	public String get_count(HashMap<String, Object> params) {
		return registDAO.get_count(params);
	}

	public boolean check_code(HashMap<String, Object> params) {
		return registDAO.check_code(params);
	}
}