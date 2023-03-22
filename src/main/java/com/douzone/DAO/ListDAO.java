package com.douzone.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.douzone.entity.IncomingVO;

@Mapper
@Repository("listDAO")
public interface ListDAO {
	List<IncomingVO> search_earner_code(HashMap<String, Object> map);

	List<IncomingVO> search_div_code(HashMap<String, Object> map);
}