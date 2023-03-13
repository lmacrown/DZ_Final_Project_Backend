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
public interface RegistDAO {
	List<IncomingVO> search_earner_code(HashMap<String, Object> map);

	List<IncomingVO> search_div_code(HashMap<String, Object> map);
}