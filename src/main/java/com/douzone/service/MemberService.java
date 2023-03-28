package com.douzone.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.Encrypt;
import com.douzone.dao.MemberDAO;
import com.douzone.entity.MemberVO;

@Service("memberService")
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;

	public MemberVO login(Map<String, Object> params) {
		Encrypt en = new Encrypt();

		String worker_id = (String)params.get("worker_id");
		String worker_pw = (String)params.get("worker_pw");
		
		String salt = get_salt(worker_id);
		String res = en.getEncrypt(worker_pw, salt);
		
		MemberVO memberVO = login(worker_id, res);
		
		if(memberVO != null) {
			memberVO.setLast_login(update_last_login(params));
			return memberVO;
		}
		return null;
	}
	
	private MemberVO login(String worker_id, String res) {
		return memberDAO.login(worker_id, res);
	}

	private String get_salt(String worker_id) {
		return memberDAO.get_salt(worker_id);
	}

	private Object update_last_login(Map<String, Object> params) {
		 memberDAO.update_last_login(params);
		 return params.get("last_login");
	}

	
}