package com.douzone.entity;

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
	private Object last_login;
}
