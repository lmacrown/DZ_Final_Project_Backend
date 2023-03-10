package com.douzone.DAO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.douzone.entity.EarnerVO;

@Mapper
@Repository("douzoneDAO")
public interface DouzoneDAO {
	
	List<Map<String, Object>>list_divcode();

	List<Map<String, Object>> earner_list(String worker_id);

	EarnerVO get_earner(Map<String, Object> params);

	void earner_insert(Map<String, Object> params);

	void earner_update(Map<String, Object> params);

	void earner_delete(Map<String, Object> params);

	
	
	List<Map<String, Object>> earner_search(Map<String, Object> params);

	List<Map<String, Object>> get_earners(Map<String, Object> task);
}



