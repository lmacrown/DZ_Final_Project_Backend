package com.douzone.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

	public void earner_insert(Map<String, Object> params) {
		douzoneDAO.earner_insert(params);

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

	@SuppressWarnings("unchecked")
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

	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> get_earners(String worker_id, HttpSession session) {
		return douzoneDAO.get_earners((Map<String, Object>) session.getAttribute("task")); 
	}

}
