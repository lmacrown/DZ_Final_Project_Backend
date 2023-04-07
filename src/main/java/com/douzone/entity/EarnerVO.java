package com.douzone.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarnerVO {
	private String worker_id;
	private String earner_code;
	private String earner_name;
	private String div_code;
	private String div_name;
	private String div_type;

	private String residence_status;
	private String is_native;
	private String personal_no;
	private String zipcode;
	private String address;
	private String address_detail;
	private String email1;
	private String email2;
	private String tel1;
	private String tel2;
	private String tel3;
	private String phone1;
	private String phone2;
	private String phone3;
	private String etc;
	
	private String is_tuition;
	private int deduction_amount;
	
	private String is_artist;
	private String artist_type;
	private float ins_reduce;
	
	private String div_modified;
}