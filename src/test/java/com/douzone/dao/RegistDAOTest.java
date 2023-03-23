package com.douzone.dao;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("registDAOTest")
public interface RegistDAOTest {
	
	String get_count(Map<String, Object> params);
	
//	List<Map<String, Object>>list_divcode();
//	
//	List<Map<String, Object>> earner_list(String worker_id);
//
//	EarnerVO get_earner(Map<String, Object> params);
//
//	void earner_form_insert(Map<String, Object> params);
//	
//	void update_count(Map<String, Object> params);
//
//	void earner_update(Map<String, Object> params);
//
//	void earner_delete(Map<String, Object> params);
//
//	int check_code(Map<String, Object> params);
}