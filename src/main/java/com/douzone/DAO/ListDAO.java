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
public interface ListDAO {
	
	List<Map<String, Object>> earner_search(Map<String, Object> params);

	List<Map<String, Object>> get_earners(Map<String, Object> params);

	List<TaxInfoVO> get_tax(HashMap<String, Object> params);

	void tax_backup(TaxInfoVO taxInfo);

	void tax_insert(HashMap<String, Object> params);

	void tax_update(HashMap<String, Object> params);

	TaxInfoVO get_tax_one(int tax_id);
}