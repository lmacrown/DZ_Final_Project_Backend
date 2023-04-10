package com.douzone.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeHistoryVO {
	//보내는 값 저장 하는 곳	
	private String div_code;
	private String div_name;
	private String div_type;
	private String earner_code;
	private String worker_id;
	
	//받아오는 값 저장 하는 곳	
	private String earner_name;
	private String div_income;
	private String old_code;
	private String new_code;
	private String modified_date;
	private String modified_by;
	private String modified_action;
	private String special_remark;
	private String mode;
	
	private String div_modified;
	
	private String old_div_code;
	private String old_div_name;

}