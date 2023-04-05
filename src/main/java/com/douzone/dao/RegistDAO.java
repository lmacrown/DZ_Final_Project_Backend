package com.douzone.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.douzone.entity.EarnerVO;
import com.douzone.entity.regist.CheckCodeVO;
import com.douzone.entity.regist.EarnerDeleteVO;
import com.douzone.entity.regist.EarnerInsertVO;
import com.douzone.entity.regist.EarnerUpdateVO;
import com.douzone.entity.regist.GetCountVO;
import com.douzone.entity.regist.GetEarnerVO;

@Mapper
@Repository("registDAO")
public interface RegistDAO {
	
	List<Map<String, Object>>list_divcode();
	
	
	List<Map<String, Object>> earner_list(String worker_id);

	EarnerVO get_earner(GetEarnerVO get_earner);//VO가 없어요

	void earner_insert(EarnerInsertVO earnerInsertVO);
	
	List<Map<String, Object>> list_occupation(String earner_type);
	
	void update_count(EarnerInsertVO earnerInsertVO);

	void earner_update(EarnerUpdateVO earnerUpdateVO);

	void earner_delete(EarnerDeleteVO earnerDeleteVO);

	String get_count(GetCountVO getCountVO);

	int check_code(CheckCodeVO checkCodeVO);

	Map<String, Object> get_occupation(HashMap<String, Object> params);

	
}