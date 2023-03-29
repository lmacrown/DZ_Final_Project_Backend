package com.douzone.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.douzone.entity.MemberVO;

@Mapper
@Repository("memberDAO")
public interface MemberDAO {

	public String get_salt(String worker_id);

	public MemberVO login(String worker_id, String res);

	void update_last_login(Map<String, Object> params);
}

