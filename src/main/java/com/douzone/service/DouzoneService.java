package com.douzone.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.DAO.DouzoneDAO;
import com.douzone.entity.EarnerVO;

@Service
public class DouzoneService {

	@Autowired
	DouzoneDAO douzoneDAO;

	void list_div(HashMap<String, Object> params) {
	}

	public List<Map<String, Object>> list_divcode() {
		return douzoneDAO.list_divcode();
	}

	public List<Map<String, Object>> earner_list(String worker_id) {
		return douzoneDAO.earner_list(worker_id);
	}

	public EarnerVO get_earner(Map<String, Object> params) {
		return douzoneDAO.get_earner(params);
	}

	public int earner_insert(Map<String, Object> params) {
		douzoneDAO.earner_insert(params);
		if((boolean) params.get("is_default")){
			douzoneDAO.update_count(params);
			return Integer.parseInt((String) params.get("earner_code"));
		}
		return 0;

	}

	public void earner_update(Map<String, Object> params) {
		douzoneDAO.earner_update(params);
	}

	public void earner_delete(Map<String, Object> params) {
		douzoneDAO.earner_delete(params);
	}

	public List<Map<String, Object>> earner_search(Map<String, Object> params) {
		return douzoneDAO.earner_search(params);
	}


	public List<Map<String, Object>> get_earners(HashMap<String, Object> params) {
		return douzoneDAO.get_earners(params); 
	}

	public String get_count(HashMap<String, Object> params) {
		return douzoneDAO.get_count(params);
	}

	public boolean check_code(HashMap<String, Object> params) {
		return douzoneDAO.check_code(params);
	}

	
	
	
	/*
	 * @SuppressWarnings("unchecked")
	public void put_task(HashMap<String, Object> params, HttpSession session) {
		Map<String,Object> task = (Map<String,Object>) session.getAttribute("task");
		List<String> earner_codes = (List<String>) params.get("earner_codes");
		if (task == null) {
			task = new HashMap<>();
			task.put("earner_codes", earner_codes);
		}
		else {
			List<String> buffer_codes = (List<String>) task.get("earner_codes");
			buffer_codes.addAll(earner_codes);
			task.put("earner_codes", buffer_codes);
		}
		
		session.setAttribute("task", task);
		System.out.println(task);
	}

	@SuppressWarnings("unchecked")
	public void delete_task(HashMap<String, Object> params, HttpSession session) {

		Map<String,Object> task = (Map<String,Object>) session.getAttribute("task");
		List<String> earner_codes = (List<String>) params.get("earner_codes");
		
		for (String earner_code : earner_codes) {
			task.remove(earner_code);
		}
		System.out.println(task);
	}
	 */
}
