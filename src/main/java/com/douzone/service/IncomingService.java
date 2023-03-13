package com.douzone.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.DAO.IncomingDAO;
import com.douzone.entity.EarnerVO;
import com.douzone.entity.IncomingVO;
import com.douzone.entity.TaxInfoVO;

@Service
public class IncomingService {

	@Autowired
	IncomingDAO incomingDAO;

	void list_div(HashMap<String, Object> params) {
	}

	public List<Map<String, Object>> list_divcode() {
		return incomingDAO.list_divcode();
	}

	public List<Map<String, Object>> earner_list(String worker_id) {
		return incomingDAO.earner_list(worker_id);
	}

	public EarnerVO get_earner(Map<String, Object> params) {
		return incomingDAO.get_earner(params);
	}

	public int earner_insert(Map<String, Object> params) {
		incomingDAO.earner_insert(params);
		if ((boolean) params.get("is_default")) {
			incomingDAO.update_count(params);
			return Integer.parseInt((String) params.get("earner_code"));
		}
		return 0;

	}

	public void earner_update(Map<String, Object> params) {
		incomingDAO.earner_update(params);
	}

	public void earner_delete(Map<String, Object> params) {
		incomingDAO.earner_delete(params);
	}

	public List<Map<String, Object>> earner_search(Map<String, Object> params) {
		return incomingDAO.earner_search(params);
	}

	public List<Map<String, Object>> get_earners(HashMap<String, Object> params) {
		return incomingDAO.get_earners(params);
	}

	public String get_count(HashMap<String, Object> params) {
		return incomingDAO.get_count(params);
	}

	public boolean check_code(HashMap<String, Object> params) {
		return incomingDAO.check_code(params);
	}

	public List<TaxInfoVO> get_tax(HashMap<String, Object> params) {
		List<TaxInfoVO> result = incomingDAO.get_tax(params);

		for (TaxInfoVO taxInfo : result) {
			if(taxInfo.getTotal_payment() == 0) {
				taxInfo.setCalculated(false);
				continue; 
			}
			taxInfo.calculate_tax();
			incomingDAO.tax_backup(taxInfo);
		}
		return result;
	}
	
	public TaxInfoVO put_tax(HashMap<String, Object> params) {
		boolean is_exist = true;
		if(null == params.get("tax_id")) {
			is_exist = false;
			System.out.println(is_exist);
		}
		
			
		
		TaxInfoVO taxInfo = new TaxInfoVO();
		if(!is_exist) {
		    int payment_ym = (int) params.get("set_date");
		    if(payment_ym>=202207) {
		    	params.put("ins_rate",0.7);
		    }
		    else {
		    	params.put("ins_rate",0.8);
		    }
		    incomingDAO.tax_insert(params);
		}
		else{
			incomingDAO.tax_update(params);
		}
		System.out.println("doing1");
		String paramName =(String) params.get("param_name");
		
		if(paramName.equals("total_payment") || (paramName.equals("tax_rate") && is_exist)){
			taxInfo = incomingDAO.get_tax_one((int) params.get("tax_id"));
			if(taxInfo.getTotal_payment() == 0) {
				taxInfo.setCalculated(false);
			}
			taxInfo.calculate_tax();
			incomingDAO.tax_backup(taxInfo);
		}
		return taxInfo;
	}
	public List<IncomingVO> search_earner_code(HashMap<String, Object> map) {
		return incomingDAO.search_earner_code(map);
	}

	public List<IncomingVO> search_div_code(HashMap<String, Object> map) {
		return incomingDAO.search_div_code(map);
	}
	
}