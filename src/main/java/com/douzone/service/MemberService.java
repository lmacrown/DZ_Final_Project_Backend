package com.douzone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.douzone.dao.MemberDAO;
import com.douzone.entity.MemberVO;

@Service("memberService")
public class MemberService implements UserDetailsService {

	@Autowired
	private MemberDAO memberDAO;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		MemberVO member = memberDAO.login(id);

		if (member == null) throw new UsernameNotFoundException("Not Found account."); 
		
		return member;
	}
}