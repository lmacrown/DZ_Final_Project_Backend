package com.douzone.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.Encrypt;
import com.douzone.dao.MemberDAO;
import com.douzone.entity.MemberVO;

@Service("memberService")
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;

	public Map<String, Object> login(Map<String, Object> params) {
		Encrypt en = new Encrypt();

		Map<String, Object> result = new HashMap<>();
		String worker_id = (String)params.get("worker_id");
		String worker_pw = (String)params.get("worker_pw");
		
		String salt = get_salt(worker_id);
		String res = en.getEncrypt(worker_pw, salt);
		
		MemberVO memberVO = login(worker_id, res);
		
		if(memberVO != null) {
			update_last_login(params);

			result.put("check_active",update_check_active(params));
			result.put("memberVO", memberVO);
			
			return result;
		}
		return null;
	}

	public boolean session_alive() {
		return true;
	}
	
	private MemberVO login(String worker_id, String res) {
		return memberDAO.login(worker_id, res);
	}

	private String get_salt(String worker_id) {
		return memberDAO.get_salt(worker_id);
	}

	private Date update_check_active(Map<String, Object> params) {
		memberDAO.update_check_active(params);
		return (Date)params.get("check_active");
	}

	private Date update_last_login(Map<String, Object> params) {
		memberDAO.update_last_login(params);
		return (Date)params.get("last_login");
	}

	
}