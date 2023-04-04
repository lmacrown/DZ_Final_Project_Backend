package com.douzone.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douzone.Aes;
import com.douzone.dao.InputDAO;

import com.douzone.entity.TaxInfoVO;
import com.douzone.entity.input.EarnerSearchVO;
import com.douzone.entity.input.GetTaskVO;
import com.douzone.entity.input.GetTaxVO;
import com.douzone.entity.input.SumTaskVO;
import com.douzone.entity.input.SumTaxVO;
import com.douzone.entity.input.TaskDeleteVO;
import com.douzone.entity.input.TaskInsertVO;
import com.douzone.entity.input.TaxInsertVO;
import com.douzone.entity.input.UpdateTaxDateVO;

@Transactional
@Service("inputService")
public class InputService {

	@Autowired
	InputDAO inputDAO;

	public List<Map<String, Object>> earner_search(EarnerSearchVO earnerSearchVO) throws Exception  {
		List<Map<String, Object>> earner_search = inputDAO.earner_search(earnerSearchVO);
		for(Map<String, Object> i : earner_search) {
			
			Aes aes = new Aes("1234567");
			if(i.get("personal_no") != null) {
				String dec = aes.decrypt((String) i.get("personal_no"));
				
				i.put("personal_no", dec);
			}
		}
		return earner_search;
	}

	public List<Map<String, Object>> get_task(GetTaskVO getTaskVO) throws Exception {
		List<Map<String, Object>> get_task = inputDAO.get_task(getTaskVO);
		for(Map<String, Object> i : get_task) {
			
			Aes aes = new Aes("1234567");
			if(i.get("personal_no") != null) {
				String dec = aes.decrypt((String) i.get("personal_no"));
				i.put("personal_no", dec);
			}
		}
		return get_task;
	}

	public List<TaxInfoVO> get_tax(GetTaxVO getTaxVO) {
		return inputDAO.get_tax(getTaxVO);
	}
	
	public Map<String,Object> update_taxinfo(Map<String, Object> params) {
		return inputDAO.update_taxinfo(params);
	}


	public void update_taxdate(UpdateTaxDateVO updateTaxDateVO) {
		inputDAO.update_taxdate(updateTaxDateVO);
	}

	public int tax_insert(TaxInsertVO taxInsertVO) {
		inputDAO.tax_insert(taxInsertVO);
		return taxInsertVO.getTax_id();
	}

	public void task_insert(TaskInsertVO taskInsertVO) {
		inputDAO.task_insert(taskInsertVO);
	}

	public void task_delete(TaskDeleteVO taskDeleteVO) {
		inputDAO.task_delete(taskDeleteVO);
	}

	public Map<String, Object> sum_task(SumTaskVO sumTaskVO) {
		return inputDAO.sum_task(sumTaskVO);
	}

	public Map<String, Object> sum_tax(SumTaxVO sumTaxVO) {
		return inputDAO.sum_tax(sumTaxVO);
	}
	
	public List<String> get_calendar(GetTaxVO getTaxVO) {
		return inputDAO.get_calendar(getTaxVO);
	}

	public void calendar_insert(GetTaxVO getTaxVO) {
		String[] accrual_ym_list = getTaxVO.getSelect_dates();
		int accrual_ym = Integer.parseInt((accrual_ym_list[0].replaceAll("-", "")).substring(0,6));

		getTaxVO.setAccrual_ym(accrual_ym);
		delete_calendar(getTaxVO);
		for(String i : accrual_ym_list) {
			getTaxVO.setSelect_date(i);
			calendar_update(getTaxVO);
		}
	}

	public void delete_calendar(GetTaxVO getTaxVO) {
		inputDAO.delete_calendar(getTaxVO);
	}

	public void calendar_update(GetTaxVO getTaxVO) {
		 inputDAO.calendar_update(getTaxVO);
	}
}