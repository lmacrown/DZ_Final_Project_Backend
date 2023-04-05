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
		for(Map<String, Object> i : search_earner) {
			System.out.println("이거"+i.get("personal_no"));
			Aes aes = new Aes("1234567");
			if(i.get("personal_no") != null) {
				String dec = aes.decrypt((String) i.get("personal_no"));
				System.out.println("복호화 :" +dec);
				i.put("personal_no", dec);
			}
		}
		return search_earner;
	}

	public List<Map<String, Object>> search_div_code(Map<String, Object> map) throws Exception {
		List<Map<String, Object>> search_div_code = listDAO.search_div_code(map);
		for(Map<String, Object> i : search_div_code) {
			System.out.println("이거"+i.get("personal_no"));
			Aes aes = new Aes("1234567");
			if(i.get("personal_no") != null) {
				String dec = aes.decrypt((String) i.get("personal_no"));
				System.out.println("복호화 :" +dec);
				i.put("personal_no", dec);
			}
		}
		return search_div_code;
//		return listDAO.search_earner_code(map);
	}
	
}