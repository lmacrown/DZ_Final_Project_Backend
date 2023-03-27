package com.douzone.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

import lombok.extern.slf4j.Slf4j;

@RestController("MemberController")
@CrossOrigin("*")
@Slf4j
public class MemberController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	GlobalResponseHandler gloabalResponseHandler;
	
	@PostMapping(value ="/login.do")
	public Map<String, Object> login(@RequestBody Map<String, Object> params, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> memberVO = new HashMap<>();
		memberVO = memberService.login(params);
		try {
			if(memberVO != null) {
				session.setAttribute("isLogon", true);
				session.setAttribute("check_active", memberVO.get("check_active"));
				result.put("member", memberService.login(params));
				result.put("isLogon", true);
			}
			else result.put("status", false);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
	}
	
//	@PostMapping(value ="/logout.do")
//	public Map<String, Object> logout(@RequestBody Map<String, Object> params, HttpSession session) {
//		Map<String, Object> result = new HashMap<>();
//		session.invalidate();
//		return gloabalResponseHandler.handleResponse(result, HttpStatus.OK);
//	}
	public static void main(String [] args) {
		String uuid = UUID.randomUUID().toString();
		System.out.println(uuid);
	}
}