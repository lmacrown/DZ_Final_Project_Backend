package com.douzone.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.douzone.entity.CodeHistoryVO;


@Mapper
@Repository("utilDAO")
public interface UtilDAO {
	int update_earner_code(CodeHistoryVO codeHistoryVO);
	
	CodeHistoryVO select_code_history(CodeHistoryVO codeHistoryVO);

}