package com.douzone.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.entity.DouzoneVO;
import com.douzone.entity.IncomingVO;
import com.douzone.service.DouzoneService;

@RestController
@CrossOrigin("*")
public class DouzoneController {

	@Autowired
	DouzoneService douzoneService;

	@Autowired
	IncomingService incomingService;
	// 사업소득자 등록

	@GetMapping(value = "/get_count")
	public Map<String, Object> get_count(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("code_count", incomingService.get_count(params));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@GetMapping(value = "/check_code")
	public Map<String, Object> check_code(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("status_code", !incomingService.check_code(params));
		result.put("time_stamp", time_stamp);

		return result;
	}

	@GetMapping(value = "/earner_list")
	public Map<String, Object> earner_list(String worker_id) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("earner_list", incomingService.earner_list(worker_id));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@GetMapping(value = "/get_earner")
	public Map<String, Object> get_earner(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();
		result.put("earner_info", incomingService.get_earner(params));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@PostMapping(value = "/earner_insert")
	public Map<String, Object> earner_insert(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("code_count", incomingService.earner_insert(params));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@PatchMapping(value = "/earner_update")
	public Map<String, Object> earner_update(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		incomingService.earner_update(params);
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@GetMapping(value = "/list_divcode")
	public Map<String, Object> list_divcode() {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("div_list", incomingService.list_divcode());
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@DeleteMapping(value = "/earner_delete")
	public Map<String, Object> earner_delete(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		incomingService.earner_delete(params);
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	// 사업소득자 자료입력

	@GetMapping(value = "/earner_search")
	public Map<String, Object> earner_search(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("earner_list", incomingService.earner_search(params));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@GetMapping(value = "/get_earners")
	public Map<String, Object> get_earners(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("earner_list", incomingService.get_earners(params));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	@GetMapping(value = "/get_tax")
	public Map<String, Object> get_tax(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("tax_list", incomingService.get_tax(params));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;

	}

	@PutMapping(value = "/put_tax")
	public Map<String, Object> put_tax(@RequestBody HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		LocalDateTime time_stamp = LocalDateTime.now();

		result.put("tax_info", incomingService.put_tax(params));
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);

		return result;
	}

	// 소득자별조회
	@GetMapping(value = "/search_earner_code")
	public Map<String, Object> search_earner_code(Locale locale, Model model, @RequestBody HashMap<String, Object> map,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		// 데이터 5개를 보내야 함
		DouzoneVO douzoneVo = (DouzoneVO) session.getAttribute("member");
		map.put("worker_id", "W00004");
		List<IncomingVO> incoming = incomingService.search_earner_code(map);

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
		List<IncomingVO> incoming = incomingService.search_div_code(map);

		result.put("earnerInfo", incoming);

		return result;
	}

}