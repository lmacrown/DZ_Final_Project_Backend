package com.douzone.controller;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
		return result;		
	}
	
}