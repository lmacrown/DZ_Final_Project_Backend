package com.douzone.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.douzone.entity.CodeHistoryVO;


@Mapper
@Repository("utilDAO")
public interface UtilDAO {
	int update_earner_code(CodeHistoryVO codeHistoryVO);
	
	int insert_code_history(CodeHistoryVO codeHistoryVO);
	
	CodeHistoryVO select_code_history(CodeHistoryVO codeHistoryVO);

}