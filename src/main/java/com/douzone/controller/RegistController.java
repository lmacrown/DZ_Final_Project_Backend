package com.douzone.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.entity.regist.CheckCodeVO;
import com.douzone.entity.regist.EarnerDeleteVO;
import com.douzone.entity.regist.EarnerInsertVO;
import com.douzone.entity.regist.EarnerUpdateVO;
import com.douzone.entity.regist.GetCountVO;
import com.douzone.entity.regist.GetEarnerVO;
import com.douzone.handler.GlobalResponseHandler;
import com.douzone.handler.RequestValidator;
import com.douzone.service.RegistService;


@RestController("registController")
@CrossOrigin("*")
public class RegistController {

	@Autowired
	RegistService registService;

	@Autowired
	GlobalResponseHandler gloabalResponseHandler;


	@PostMapping(value = "/regist/get_count")
	public Map<String, Object> get_count(@Valid @RequestBody GetCountVO getCountVO) {
		Map<String, Object> result = new HashMap<>();
		result.put("code_count", registService.get_count(getCountVO));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@PostMapping(value = "/regist/check_code")
	public Map<String, Object> check_code(@Valid @RequestBody CheckCodeVO checkCodeVO) {
		Map<String, Object> result = new HashMap<>();
		result.put("code_count", registService.check_code(checkCodeVO));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@GetMapping(value = "/regist/earner_list/{worker_id}")
	public Map<String, Object> earner_list(Model model, @PathVariable String worker_id) {
		Map<String, Object> result = new HashMap<>();
		result.put("earner_list", registService.earner_list(worker_id));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@PostMapping(value = "/regist/get_earner")
	public Map<String, Object> get_earner(@Valid @RequestBody GetEarnerVO getEarnerVO) {
		Map<String, Object> result = new HashMap<>();
		result.put("earner_info", registService.get_earner(getEarnerVO));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@PostMapping(value = "/regist/earner_insert")
	public Map<String, Object> earner_insert(@Valid @RequestBody EarnerInsertVO earnerInsertVO) {
		Map<String, Object> result = new HashMap<>();
		result.put("code_count", registService.earner_insert(earnerInsertVO));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}
	//공부해야될 것
	@PatchMapping(value = "/regist/earner_update")
	public Map<String, Object> earner_update(@Valid @RequestBody EarnerUpdateVO earnerUpdateVO) {
		Map<String, Object> result = new HashMap<>();
		
		RequestValidator validator = new RequestValidator();
        Errors errors = new BeanPropertyBindingResult(earnerUpdateVO, "earnerUpdateVO");
        validator.validate(earnerUpdateVO, errors);

        if (errors.hasErrors()) {
        	throw new NotReadablePropertyException(RegistController.class, "earnerUpdateVO", "Invalid Update Parameter");
        } else {
        	registService.earner_update(earnerUpdateVO);
        }
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@GetMapping(value = "/regist/list_divcode")
	public Map<String, Object> list_divcode() {
		Map<String, Object> result = new HashMap<>();
		result.put("div_list", registService.list_divcode());
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/regist/earner_delete")
	public Map<String, Object> earner_delete(@Valid @RequestBody EarnerDeleteVO earnerDeleteVO) {
		Map<String, Object> result = new HashMap<>();
		registService.earner_delete(earnerDeleteVO);
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}
}