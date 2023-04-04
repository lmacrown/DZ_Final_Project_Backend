package com.douzone.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxInfoVO {
	private int tax_id;
	private String worker_id;
	private String earner_code;

	private float tax_rate;
	private int accrual_ym;
	private int payment_ym;
	private int payment_date;
	private int total_payment;
	
	private int tax_income;
	private int tax_local;
	private int tax_total;
	
	private int tuition_amount;
	
	private int artist_cost;
	private int ins_cost;
	
	private int sworker_cost;
	private int sworker_ins;
	private int workinjury_ins;
	
	private int real_payment;
}