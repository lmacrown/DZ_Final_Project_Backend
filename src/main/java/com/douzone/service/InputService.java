package com.douzone.service;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douzone.dao.InputDAO;
import com.douzone.entity.EarnerTaxVO;
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

	public List<Map<String, Object>> earner_search(EarnerSearchVO earnerSearchVO) {
		return inputDAO.earner_search(earnerSearchVO);
	}

	public List<Map<String, Object>> get_task(GetTaskVO getTaskVO) {
		return inputDAO.get_task(getTaskVO);
	}

	public List<TaxInfoVO> get_tax(GetTaxVO getTaxVO) {
		return inputDAO.get_tax(getTaxVO);
	}
	
	public EarnerTaxVO update_taxinfo(Map<String, Object> params) {
	      params.put("result", null);
	      inputDAO.update_taxinfo(params);
	      ResultSet rs = (ResultSet) params.get("result");
	      try {
	         while (rs.next()) {
	            EarnerTaxVO earnerTax = new EarnerTaxVO(
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
	            return earnerTax;
	         }
	      } catch (Exception e) {
	         System.out.println(e.getMessage());// 로그로 고칠 것
	      }
	      return null;
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

}