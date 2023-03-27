package com.douzone.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
	
	private String worker_id;
	private String corp_code;
	private String worker_name;
	private int code_count;
	private Date last_login;
	private Date check_active;
}
