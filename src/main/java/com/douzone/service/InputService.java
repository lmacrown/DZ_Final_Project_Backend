package com.douzone.service;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douzone.DAO.InputDAO;
import com.douzone.entity.TaxInfoVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class InputService {

	@Autowired
	InputDAO inputDAO;

	public List<Map<String, Object>> earner_search(Map<String, Object> params) {
		return inputDAO.earner_search(params);
	}

	public List<Map<String, Object>> get_earners(HashMap<String, Object> params) {
		return inputDAO.get_earners(params);
	}

	public List<TaxInfoVO> get_tax(HashMap<String, Object> params) {
		//get_tax조회시 inner join 한 이유
		return inputDAO.get_tax(params);
		//select & cal
	}

	public  Map<String, Object> put_tax(HashMap<String, Object> params) {
		boolean is_exist = true;
		Map<String, Object> result = new HashMap<>();
		if (null==params.get("tax_id")) is_exist = false;
		
		if (!is_exist) 
			tax_insert(params);
		else {
			params.put("result", null);
			String payment_date = (String)params.get("payment_date");//이 값을 db에서 select 해서 가져와서 비교?
			String accrual_ym = (String)params.get("accrual_ym");//프론트에서 값을 넘겨줄때 파람 변경 값을 보내주는 게 낫지않나?
			if(1>0) {
				try {
				update_info(params);
				result.put("earner_tax",null);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}else {
				try {
					update_tax(params);//update : 날짜 바뀔 때와 금액 바뀔때로 나눔
					ResultSet rs = (ResultSet)params.get("result");//뒤부터 값 확인 코드 => 결과 확인 후 삭제
					result.put("status", true);
					result.put("earner_tax", rs);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
//			try{
//		        while (rs.next()){
//		        	String is_tuition = rs.getString("is_tuition");
//		        	String deduction_amount = rs.getString("deduction_amount");
//		        	String is_artist = rs.getString("is_artist");
//		        	String ins_reduce = rs.getString("ins_reduce");
//		        	String tax_id = rs.getString("tax_id");
//		        	String tax_rate = rs.getString("tax_rate");
//		        	String ins_rate = rs.getString("ins_rate");
//		        	String total_payment = rs.getString("total_payment");
//		        	String accrual_ym = rs.getString("accrual_ym");
//		        	String payment_ym = rs.getString("payment_ym");
//		        	String payment_date = rs.getString("payment_date");
//		        	String calculated = rs.getString("calculated");
//		        	String tax_income = rs.getString("tax_income");
//		        	String tax_local = rs.getString("tax_local");
//		        	String artist_cost = rs.getString("artist_cost");
//		        	String ins_cost = rs.getString("ins_cost");
//		        	String tax_total = rs.getString("tax_total");
//		        	String real_payment = rs.getString("real_payment");
//		        	System.out.println("is_tuition: " + is_tuition);
//		        	System.out.println("deduction_amount: " + deduction_amount);
//		        	System.out.println("is_artist: " + is_artist);
//		        	System.out.println("ins_reduce: " + ins_reduce);
//		        	System.out.println("tax_id: " + tax_id);
//		        	System.out.println("tax_rate: " + tax_rate);
//		        	System.out.println("ins_rate: " + ins_rate);
//		        	System.out.println("total_payment: " + total_payment);
//		        	System.out.println("accrual_ym: " + accrual_ym);
//		        	System.out.println("payment_ym: " + payment_ym);
//		        	System.out.println("payment_date: " + payment_date);
//		        	System.out.println("calculated: " + calculated);
//		        	System.out.println("tax_income: " + tax_income);
//		        	System.out.println("tax_local: " + tax_local);
//		        	System.out.println("artist_cost: " + artist_cost);
//		        	System.out.println("ins_cost: " + ins_cost);
//		        	System.out.println("tax_total: " + tax_total);
//		        	System.out.println("real_payment: " + real_payment);// 삭제
//		        }
//		     }catch (Exception e ){
//		         System.out.println(e.getMessage());//로그로 고칠 것
//		     }
		}
		return result;
	}
	
	private void update_info(HashMap<String, Object> params) {
		inputDAO.update_info(params);
	}

	public List<TaxInfoVO> get_tax_one(HashMap<String, Object> params) {
		return inputDAO.get_tax_one(params);
	}
	
	public void tax_insert(HashMap<String, Object> params) {
		inputDAO.tax_insert(params);
	}
	
	public  void update_tax(HashMap<String, Object> params) {
		//inputDAO.tax_update(params);
		inputDAO.tax_backup(params);//정보 넣고 백업
//		System.out.println(params);
//		 ResultSet rs = (ResultSet)params.get("result");
//		 System.out.println(rs);
//		  try{
//	            while (rs.next()){
//	                String empNo = rs.getString("is_tuition");
//	                String tax_id = rs.getString("tax_id");
//	                System.out.println(empNo);
//	                System.out.println(tax_id);
//	            }
//	        }catch (Exception e ){
//	            System.out.println(e.getMessage());
//	        }
	}

}