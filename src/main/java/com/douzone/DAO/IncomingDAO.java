package com.douzone.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.douzone.entity.EarnerVO;
import com.douzone.entity.IncomingVO;
import com.douzone.entity.TaxInfoVO;

@Mapper
@Repository("IncomingDAO")
public interface IncomingDAO {
	
	List<Map<String, Object>>list_divcode();

	List<Map<String, Object>> earner_list(String worker_id);

	EarnerVO get_earner(Map<String, Object> params);

	void earner_insert(Map<String, Object> params);
	
	void update_count(Map<String, Object> params);

	void earner_update(Map<String, Object> params);

	void earner_delete(Map<String, Object> params);

	List<IncomingVO> search_earner_code(HashMap<String, Object> map);

	List<IncomingVO> search_div_code(HashMap<String, Object> map);
	
	List<Map<String, Object>> earner_search(Map<String, Object> params);

	List<Map<String, Object>> get_earners(Map<String, Object> params);

	String get_count(HashMap<String, Object> params);

	boolean check_code(HashMap<String, Object> params);

	List<TaxInfoVO> get_tax(HashMap<String, Object> params);

	void tax_backup(TaxInfoVO taxInfo);

	void tax_insert(HashMap<String, Object> params);

	void tax_update(HashMap<String, Object> params);

	TaxInfoVO get_tax_one(int tax_id);

	
	//String get_count(String worker_id);
}