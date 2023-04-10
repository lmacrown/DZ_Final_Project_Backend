package com.douzone.service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.Aes;
import com.douzone.dao.UtilDAO;
import com.douzone.entity.CodeHistoryVO;
import com.douzone.entity.EarnerVO;
import com.douzone.entity.regist.EarnerUpdateVO;

@Service("utilService")
public class UtilService {

	@Autowired
	UtilDAO utilDAO;
	
	public int update_earner_code(CodeHistoryVO codeHistoryVO) {	
		return utilDAO.update_earner_code(codeHistoryVO);			
	}
	
	public CodeHistoryVO select_code_history(CodeHistoryVO codeHistoryVO) {
		return utilDAO.select_code_history(codeHistoryVO);
	}
	
	
	public static void decrypt_one(EarnerVO result) throws Exception {
		if (null == result) 
	        throw new NoSuchElementException("No earner found with the given parameters");
		String personal_no = result.getPersonal_no();
		if(personal_no != null) {
			String key = result.getBw_key();
			Aes aes = new Aes(key);
			String dec = aes.decrypt(personal_no);
			result.setPersonal_no(dec);
		}
	}
	
	public static void decrypt_list(List<Map<String, Object>> earner_list) throws Exception {
		for(Map<String, Object> i : earner_list) {
			if(i.get("personal_no") != null) {
				String key = (String) i.get("bw_key");
				Aes aes = new Aes(key);
				String dec = aes.decrypt((String) i.get("personal_no"));
				i.put("personal_no", dec);
			}
		}
	}
	
	public static EarnerUpdateVO encryption(EarnerUpdateVO earnerUpdateVO, String key) throws Exception {
		String param_name = earnerUpdateVO.getParam_name();
		if(param_name.equals("personal_no")) {
			String enc_target = earnerUpdateVO.getParam_value();
			
			Aes secure = new Aes(key);
			String enc = secure.encrypt(enc_target);
			earnerUpdateVO.setParam_value(enc);
		}
		return earnerUpdateVO;
	}

	
}