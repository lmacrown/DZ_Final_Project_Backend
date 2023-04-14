package com.douzone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.douzone.dao.UtilDAO;
import com.douzone.entity.CodeHistoryVO;

@Service("utilService")
public class UtilService {

	@Autowired
	UtilDAO utilDAO;
	
	public String update_earner_code(CodeHistoryVO codeHistoryVO) {	
		 utilDAO.update_earner_code(codeHistoryVO);	
		 int count = utilDAO.insert_code_history(codeHistoryVO);
		 if (count != 0) {
			 return utilDAO.select_earner_div_modified(codeHistoryVO);
		 } 
		 return "";
	}
	
	public CodeHistoryVO select_code_history(CodeHistoryVO codeHistoryVO) {
		return utilDAO.select_code_history(codeHistoryVO);
	}
	
}