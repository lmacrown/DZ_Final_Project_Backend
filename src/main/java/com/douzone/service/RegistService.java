package com.douzone.service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<Map<String, Object>> earner_list(String worker_id) {
		return registDAO.earner_list(worker_id);
	}

	public EarnerVO get_earner(GetEarnerVO get_earner) {
		EarnerVO result = registDAO.get_earner(get_earner);
	    if (null == result) 
	        throw new NoSuchElementException("No earner found with the given parameters");
	    
	    
	    return result;
	}

	public int earner_insert(EarnerInsertVO earnerInsertVO) {
		registDAO.earner_insert(earnerInsertVO);
		if ((int)earnerInsertVO.getIs_default()==1) {
			update_count(earnerInsertVO);
			return Integer.parseInt((String) earnerInsertVO.getEarner_code());
		}
		return 0;
	}
	
	private void update_count(EarnerInsertVO earnerInsertVO) {
		registDAO.update_count(earnerInsertVO);
	}
	
	public void earner_update(EarnerUpdateVO earnerUpdateVO) {
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
}