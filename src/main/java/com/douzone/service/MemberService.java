package com.douzone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.douzone.DAO.MemberDAO;
import com.douzone.entity.DouzoneVO;
import com.douzone.entity.MemberVO;

@Service
public class MemberService implements UserDetailsService {

	@Autowired
	private MemberDAO memberDAO;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		System.out.println("호출");
		MemberVO member = memberDAO.findByEmail(id);
		System.out.println("호출 성공");
		if (member == null) throw new UsernameNotFoundException("Not Found account."); 
		
		return member;
	}
}