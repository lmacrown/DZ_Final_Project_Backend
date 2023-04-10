package com.douzone.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.douzone.entity.EarnerVO;
import com.douzone.entity.regist.CheckCodeVO;
import com.douzone.entity.regist.EarnerDeleteVO;
import com.douzone.entity.regist.EarnerInsertVO;
import com.douzone.entity.regist.EarnerUpdateVO;
import com.douzone.entity.regist.GetCountVO;
import com.douzone.entity.regist.GetEarnerVO;
import com.douzone.entity.regist.GetOccupationVO;
import com.douzone.entity.regist.ListOccupationVO;

@Mapper
@Repository("registDAO")
public interface RegistDAO {
	
	List<Map<String, Object>>list_divcode(String search_value);
	
	List<Map<String, Object>> list_occupation(ListOccupationVO listOccupationVO);
	
	List<Map<String, Object>> earner_list(String worker_id);

	EarnerVO get_earner(GetEarnerVO get_earner);//VO가 없어요

	void earner_insert(EarnerInsertVO earnerInsertVO);
	
	void update_count(EarnerInsertVO earnerInsertVO);

	void earner_update(EarnerUpdateVO earnerUpdateVO);

	void earner_delete(EarnerDeleteVO earnerDeleteVO);

	String get_count(GetCountVO getCountVO);

	int check_code(CheckCodeVO checkCodeVO);

	Map<String, Object> get_occupation(GetOccupationVO getOccupationVO);

	
}