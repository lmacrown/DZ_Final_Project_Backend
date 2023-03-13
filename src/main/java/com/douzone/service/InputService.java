package com.douzone.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.DAO.inputDAO;
import com.douzone.entity.TaxInfoVO;

@Service
public class InputService {

	@Autowired
	inputDAO inputDAO;

	public List<Map<String, Object>> earner_search(Map<String, Object> params) {
		return inputDAO.earner_search(params);
	}

	public List<Map<String, Object>> get_earners(HashMap<String, Object> params) {
		return inputDAO.get_earners(params);
	}

	public List<TaxInfoVO> get_tax(HashMap<String, Object> params) {
		List<TaxInfoVO> result = inputDAO.get_tax(params);

		for (TaxInfoVO taxInfo : result) {
			if (taxInfo.getTotal_payment() == 0) {
				taxInfo.setCalculated(false);
				continue;
			}
			taxInfo.calculate_tax();
			inputDAO.tax_backup(taxInfo);
		}
		return result;
	}

	public TaxInfoVO put_tax(HashMap<String, Object> params) {
		boolean is_exist = true;
		if (null == params.get("tax_id")) {
			is_exist = false;
			System.out.println(is_exist);
		}

		TaxInfoVO taxInfo = new TaxInfoVO();
		if (!is_exist) {
			int payment_ym = (int) params.get("set_date");
			if (payment_ym >= 202207) {
				params.put("ins_rate", 0.7);
			} else {
				params.put("ins_rate", 0.8);
			}
			inputDAO.tax_insert(params);
		} else {
			inputDAO.tax_update(params);
		}
		System.out.println("doing1");
		String paramName = (String) params.get("param_name");

		if (paramName.equals("total_payment") || (paramName.equals("tax_rate") && is_exist)) {
			taxInfo = inputDAO.get_tax_one((int) params.get("tax_id"));
			if (taxInfo.getTotal_payment() == 0) {
				taxInfo.setCalculated(false);
			}
			taxInfo.calculate_tax();
			inputDAO.tax_backup(taxInfo);
		}
		return taxInfo;
	}

}