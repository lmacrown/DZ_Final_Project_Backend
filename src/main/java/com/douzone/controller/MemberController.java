package com.douzone.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.entity.MemberVO;
import com.douzone.handler.GlobalResponseHandler;
import com.douzone.service.MemberService;

@RestController("MemberController")
@CrossOrigin("*")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	GlobalResponseHandler gloabalResponseHandler;
	
	@PostMapping(value ="/login.do")
	public Map<String, Object> login(@RequestBody Map<String, Object> params, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		MemberVO memberVO = memberService.login(params);
		try {
			if(memberVO != null) {
				session.setAttribute("isLogon", true);
				result.put("MemberVO", memberService.login(params));
				result.put("isLogon", true);
			}
			else result.put("status", false);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}
	
//	public static void main(String [] args) {
//		String uuid = UUID.randomUUID().toString();
//		System.out.println(uuid);
//	}
}