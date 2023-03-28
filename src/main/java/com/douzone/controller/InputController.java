package com.douzone.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.entity.input.EarnerSearchVO;
import com.douzone.entity.input.GetTaskVO;
import com.douzone.entity.input.GetTaxVO;
import com.douzone.entity.input.SumTaskVO;
import com.douzone.entity.input.SumTaxVO;
import com.douzone.entity.input.TaskDeleteVO;
import com.douzone.entity.input.TaskInsertVO;
import com.douzone.entity.input.TaxInsertVO;
import com.douzone.entity.input.UpdateTaxDateVO;
import com.douzone.handler.GlobalResponseHandler;
import com.douzone.service.InputService;

@RestController("inputController")
@CrossOrigin("*")
public class InputController {

	@Autowired
	InputService inputService;

	@Autowired
	GlobalResponseHandler gloabalResponseHandler;

	@PostMapping(value = "/input/earner_search")
	public Map<String, Object> earner_search(@Valid @RequestBody EarnerSearchVO earnerSearchVO) {
		Map<String, Object> result = new HashMap<>();
		result.put("earner_list", inputService.earner_search(earnerSearchVO));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@PostMapping(value = "/input/get_task")
	public Map<String, Object> get_task(@Valid @RequestBody GetTaskVO getTaskVO) {
		Map<String, Object> result = new HashMap<>();
		List<Map<String, Object>> task_list = inputService.get_task(getTaskVO);
		result.put("task_list", task_list);
		result.put("task_count", task_list.size());
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/input/sum_task")
	public Map<String, Object> sum_task(@Valid @RequestBody SumTaskVO sumTaskVO) {
		Map<String, Object> result = new HashMap<>();
		result.put("sum_task", inputService.sum_task(sumTaskVO));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}
	@PostMapping(value = "/input/sum_tax")
	public Map<String, Object> get_taskinfo(@Valid @RequestBody SumTaxVO sumTaxVO) {
		Map<String, Object> result = new HashMap<>();
		result.put("sum_tax", inputService.sum_tax(sumTaxVO));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@PostMapping(value = "/input/task_insert")
	public Map<String, Object> task_insert(@Valid @RequestBody TaskInsertVO taskInsertVO) {
		Map<String, Object> result = new HashMap<>();
		inputService.task_insert(taskInsertVO);
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/input/task_delete")
	public Map<String, Object> task_delete(@Valid @RequestBody TaskDeleteVO taskDeleteVO) {
		Map<String, Object> result = new HashMap<>();
		inputService.task_delete(taskDeleteVO);
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@PostMapping(value = "/input/get_tax")
	public Map<String, Object> get_tax(@Valid @RequestBody GetTaxVO getTaxVO) {
		Map<String, Object> result = new HashMap<>();
		result.put("tax_list", inputService.get_tax(getTaxVO));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@PostMapping(value = "/input/tax_insert")
	public Map<String, Object> tax_insert(@Valid @RequestBody TaxInsertVO taxInsertVO) {
		Map<String, Object> result = new HashMap<>();
		result.put("tax_id", inputService.tax_insert(taxInsertVO));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@PatchMapping(value = "/input/update_taxdate")
	public Map<String, Object> update_taxdate(@Valid @RequestBody UpdateTaxDateVO updateTaxDateVO) {
		Map<String, Object> result = new HashMap<>();
		inputService.update_taxdate(updateTaxDateVO);
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	@PatchMapping(value = "/input/update_taxinfo")
	public Map<String, Object> update_taxinfo(@RequestBody Map<String, Object> params) throws SQLException {
		Map<String, Object> result = new HashMap<>();
		System.out.println(params);
		validateInput(params);
		System.out.println(params);
		result.put("earner_tax", inputService.update_taxinfo(params));
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}

	private void validateInput(Map<String, Object> params) {
		if (!params.containsKey("total_payment") || !params.containsKey("tax_rate") || !params.containsKey("tax_id")) {
			throw new IllegalArgumentException("Missing required fields: total_payment, tax_rate, or tax_id");
		}

		int totalPayment = (int) params.get("total_payment");
		double taxRate = (double) params.get("tax_rate");
		int taxId = (int) params.get("tax_id");

		if (totalPayment <= 0 || taxRate <= 0 || taxRate >= 10 || taxId <= 0) {
			throw new IllegalArgumentException("Invalid input: Check your total_payment, tax_rate, and tax_id values");
		}
	}

}
