package com.douzone.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD:src/main/java/com/douzone/dao/ListDAO.java
import com.douzone.entity.IncomingVO;

=======

import com.douzone.entity.IncomingVO;
>>>>>>> main:src/main/java/com/douzone/DAO/ListDAO.java

@Mapper
@Repository("listDAO")
public interface ListDAO {
	List<IncomingVO> search_earner_code(HashMap<String, Object> map);

	List<IncomingVO> search_div_code(HashMap<String, Object> map);
}