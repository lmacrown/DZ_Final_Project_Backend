package com.douzone.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.entity.DouzoneVO;
import com.douzone.entity.IncomingVO;
import com.douzone.service.ListService;

import lombok.extern.slf4j.Slf4j;

@RestController("listController")
@CrossOrigin("*")
public class ListController {

	@Autowired
	ListService listService;

	// 소득자별조회
	@GetMapping(value = "/search_earner_code")
	public Map<String, Object> search_earner_code(Locale locale, Model model, @RequestBody HashMap<String, Object> map,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		// 데이터 5개를 보내야 함
		DouzoneVO douzoneVo = (DouzoneVO) session.getAttribute("member");
		map.put("worker_id", "W00004");
		List<IncomingVO> incoming = listService.search_earner_code(map);

		result.put("earnerInfo", incoming);

		return result;
	}

	// 소득구분별조회
	@GetMapping(value = "/search_div_code")
	public Map<String, Object> search_div_code(Locale locale, Model model, @RequestBody HashMap<String, Object> map,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		// 데이터 5개를 보내야 함
		DouzoneVO douzoneVo = (DouzoneVO) session.getAttribute("member");
		map.put("worker_id", "W00004");
		List<IncomingVO> incoming = listService.search_div_code(map);

		result.put("earnerInfo", incoming);

		return result;
	}

}