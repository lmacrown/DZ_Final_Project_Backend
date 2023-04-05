package com.douzone.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("listDAO")
public interface ListDAO {
	List<Map<String, Object>> search_earner_code(Map<String, Object> map);

	List<Map<String, Object>> search_div_code(Map<String, Object> map);
}