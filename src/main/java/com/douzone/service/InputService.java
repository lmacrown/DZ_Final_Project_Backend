package com.douzone.service;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douzone.dao.InputDAO;
import com.douzone.entity.EarnerTaxVO;
import com.douzone.entity.TaxInfoVO;

@Transactional
@Service("inputService")
public class InputService {

	@Autowired
	InputDAO inputDAO;

	public List<Map<String, Object>> earner_search(Map<String, Object> params) {
		return inputDAO.earner_search(params);
	}

	public List<Map<String, Object>> get_task(Map<String, Object> params) {
		return inputDAO.get_task(params);
	}

	public List<TaxInfoVO> get_tax(Map<String, Object> params) {
		return inputDAO.get_tax(params);
	}

	public EarnerTaxVO update_taxinfo(Map<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		params.put("result", null);
		EarnerTaxVO earnerTax = null;
		
		inputDAO.update_taxinfo(params);
		ResultSet rs = (ResultSet) params.get("result");// 뒤부터 값 확인 코드 => 결과 확인 후 삭제
		try {
			while (rs.next()) {
				earnerTax = new EarnerTaxVO(
					rs.getString("is_tuition"),
					rs.getString("deduction_amount"),
					rs.getString("is_artist"),
					rs.getString("ins_reduce"),
					rs.getString("tax_id"),
					rs.getString("tax_rate"),
					rs.getString("ins_rate"),
					rs.getString("total_payment"),
					rs.getString("accrual_ym"),
					rs.getString("payment_ym"),
					rs.getString("payment_date"),
					rs.getString("calculated"),
					rs.getString("tax_income"),
					rs.getString("tax_local"),
					rs.getString("artist_cost"),
					rs.getString("ins_cost"),
					rs.getString("tax_total"),
					rs.getString("real_payment")
					
				);
				result.put("?", earnerTax);
			}
		
		} catch (Exception e) {
			System.out.println(e.getMessage());// 로그로 고칠 것
		}
		return earnerTax;
	}

	public void update_taxdate(Map<String, Object> params) {
		inputDAO.update_taxdate(params);
	}

	public int  tax_insert(Map<String, Object> params) {
		inputDAO.tax_insert(params);
		return (int)params.get("tax_id");
	}

	public void task_insert(Map<String, Object> params) {
		inputDAO.task_insert(params);
	}

	public void task_delete(Map<String, Object> params) {
		inputDAO.task_delete(params);
	}



}