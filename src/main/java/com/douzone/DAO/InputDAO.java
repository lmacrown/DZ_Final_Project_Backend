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
@Repository("inputDAO")
public interface InputDAO {
	
	List<Map<String, Object>> earner_search(Map<String, Object> params);

	List<Map<String, Object>> get_earners(Map<String, Object> params);

	List<TaxInfoVO> get_tax(Map<String, Object> params);
	
	List<TaxInfoVO> get_tax_one(Map<String, Object> params);

	void tax_backup(Map<String, Object> params);

	void tax_insert(Map<String, Object> params);

	void tax_update(Map<String, Object> params);

	TaxInfoVO get_tax_one(int tax_id);
}