package com.douzone.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.douzone.entity.MemberVO;

@Mapper
@Repository("memberDAO")
public interface MemberDAO {

	public int updateMemberLastLogin(String id);
	public MemberVO login(String id);
	
}

