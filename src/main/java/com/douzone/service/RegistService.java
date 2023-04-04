package com.douzone.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.Aes;
import com.douzone.dao.RegistDAO;
import com.douzone.entity.EarnerVO;
import com.douzone.entity.regist.CheckCodeVO;
import com.douzone.entity.regist.EarnerDeleteVO;
import com.douzone.entity.regist.EarnerInsertVO;
import com.douzone.entity.regist.EarnerUpdateVO;
import com.douzone.entity.regist.GetCountVO;
import com.douzone.entity.regist.GetEarnerVO;

@Service("registService")
public class RegistService {

	@Autowired
	RegistDAO registDAO;

	public List<Map<String, Object>> list_divcode() {
		return registDAO.list_divcode();
	}

	public List<Map<String, Object>> earner_list(String worker_id) throws Exception {
		List<Map<String, Object>> earner_list = registDAO.earner_list(worker_id);
		for(Map<String, Object> i : earner_list) {
			System.out.println("이거"+i.get("personal_no"));
			Aes aes = new Aes("1234567");
			if(i.get("personal_no") != null) {
				String dec = aes.decrypt((String) i.get("personal_no"));
				System.out.println("복호화 :" +dec);
				i.put("personal_no", dec);
			}
		}
		return earner_list;
//		return registDAO.earner_list(worker_id);
	}

	public EarnerVO get_earner(GetEarnerVO get_earner) {
		EarnerVO result = registDAO.get_earner(get_earner);
	    if (null == result) 
	        throw new NoSuchElementException("No earner found with the given parameters");
	    return result;
	}

	public int earner_insert(EarnerInsertVO earnerInsertVO) {
		registDAO.earner_insert(earnerInsertVO);//SRP 지킬것
		if ((int)earnerInsertVO.getIs_default()==1) {
			update_count(earnerInsertVO);
			return Integer.parseInt((String) earnerInsertVO.getEarner_code());
		}
		return 0;
	}
	
	private void update_count(EarnerInsertVO earnerInsertVO) {
		registDAO.update_count(earnerInsertVO);
	}
	
	public void earner_update(EarnerUpdateVO earnerUpdateVO) throws Exception {
		//System.out.println("earner_update 실행");
		String param_name = earnerUpdateVO.getParam_name();
		if(param_name.equals("personal_no")) {
			String enc_target = earnerUpdateVO.getParam_value();
			//System.out.println(enc_target);
			Aes aes = new Aes("1234567");
			String enc = aes.encrypt(enc_target);
			earnerUpdateVO.setParam_value(enc);
		}
		registDAO.earner_update(earnerUpdateVO);
	}

	public void earner_delete(EarnerDeleteVO earnerDeleteVO) {
		registDAO.earner_delete(earnerDeleteVO);
	}

	public String get_count(GetCountVO getCountVO) {
		return registDAO.get_count(getCountVO);
	}

	public int check_code(CheckCodeVO checkCodeVO) {
		return registDAO.check_code(checkCodeVO);
	}

	public List<Map<String, Object>> list_occupation() {
		return registDAO.list_occupation();
	}

	public Map<String, Object> get_occupation(HashMap<String, Object> params) {
		return registDAO.get_occupation(params);
	}
}