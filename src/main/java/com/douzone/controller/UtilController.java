package com.douzone.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.entity.CodeHistoryVO;
import com.douzone.handler.GlobalResponseHandler;
import com.douzone.service.UtilService;


@RestController("utilController")
@CrossOrigin("*")
public class UtilController {

	@Autowired
	UtilService utilService;
	@Autowired
	GlobalResponseHandler gloabalResponseHandler;
		
	//코드변환
	@PostMapping(value="/util/update_earner_code")
	public Map<String, Object> update_earner_code(@RequestBody CodeHistoryVO codeHistoryVO){		
		int count = utilService.update_earner_code(codeHistoryVO);		
		Map<String, Object> result = new HashMap<>();
		if (count != 0) {
			result.put("status", true);
			result.put("message", "수정되었습니다.");				
		} else {
			result.put("status", false);
			result.put("message", "오류가 발생했습니다.");
		}		
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);

	}
	
	//코드 변환이력
	@PostMapping(value = "/util/select_code_history")
	public Map<String, Object> select_code_history( @RequestBody CodeHistoryVO codeHistoryVO) {
		Map<String, Object> result = new HashMap<>();
		CodeHistoryVO modified_date = utilService.select_code_history(codeHistoryVO);
		result.put("code_history", modified_date);
		
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}
	
}