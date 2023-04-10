package com.douzone.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.douzone.dao.UtilDAO;
import com.douzone.entity.CodeHistoryVO;

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
	
}