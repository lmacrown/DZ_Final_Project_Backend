package com.douzone.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.DAO.DouzoneDAO;
import com.douzone.entity.EarnerVO;
import com.douzone.entity.TaxInfoVO;

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
		if ((boolean) params.get("is_default")) {
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

	public List<TaxInfoVO> get_tax(HashMap<String, Object> params) {
		List<TaxInfoVO> result = douzoneDAO.get_tax(params);

		for (TaxInfoVO taxInfo : result) {
			if(taxInfo.getTotal_payment() == 0) {
				taxInfo.setCalculated(false);
				continue; 
			}
			calculate_tax(taxInfo);
		}
		return result;
	}
	
	public TaxInfoVO put_tax(HashMap<String, Object> params) {
		String tax_id = (String) params.get("tax_id");
		TaxInfoVO taxInfo = new TaxInfoVO();
		if(tax_id == null) {
			douzoneDAO.tax_insert(params);
			taxInfo.setTax_id((String) params.get("tax_id"));
		}
		else{
			douzoneDAO.tax_update(params);
		}
		
		String paramName =(String) params.get("param_name");
		
		if(paramName.equals("total_payment") || (paramName.equals("tax_rate") && tax_id != null)){
			taxInfo = douzoneDAO.get_tax_one((String) params.get("tax_id"));
			if(taxInfo.getTotal_payment() == 0) {
				taxInfo.setCalculated(false);
			}
			calculate_tax(taxInfo);
			return taxInfo;
		}
		return null;
	}
	
	public void calculate_tax(TaxInfoVO taxInfo) {
		int totalPayment = taxInfo.getTotal_payment(); // 지급총액
		float taxRate = taxInfo.getTax_rate(); // 세율
		
		// 고용 보험료, 예술인 경비계산
		int artistCost = (int)(totalPayment * 0.25);
		taxInfo.setArtist_cost(artistCost);
		
		int insCost = (int) ((totalPayment - artistCost) * taxInfo.getIns_rate() * taxInfo.getIns_reduce());
		if (insCost < 220570)
			taxInfo.setIns_cost(insCost);
		else
			taxInfo.setIns_cost(220570);
		
		// 학자금 
		taxInfo.setTutition_amount(taxInfo.getDeduction_amount());
		
		// 소득세 계산
		int taxIncome = (int)(totalPayment * taxRate);
		taxInfo.setTax_income(taxIncome);
		// 지방소득세 계산
		int localIncome = (int) (taxIncome * 0.1);
		taxInfo.setTax_local(localIncome);
		// 세액계 계산
		int totalTax = taxIncome+localIncome+taxInfo.getDeduction_amount();
		taxInfo.setTax_total(totalTax);
		// 차인지급액 계산
		taxInfo.setReal_payment(totalPayment-(totalTax+insCost));
		// 계산 값 백업
		douzoneDAO.tax_backup(taxInfo);
		taxInfo.setCalculated(true);
	}

	
}
