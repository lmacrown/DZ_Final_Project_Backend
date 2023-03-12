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
	private String tax_id;
	private String worker_id;
	private String earner_code;
	
	private boolean calculated;
	
	private String is_tuition;
	private int deduction_amount;

	private String is_artist;
	private float ins_reduce;
	private float ins_rate;

	private float tax_rate;
	private int accrual_ym;
	private int payment_ym;
	private int payment_date;
	private int total_payment;
	
	private int tax_income;
	private int tax_local;
	private int tax_total;
	private int ins_cost;
	private int artist_cost;
	private int tutition_amount;
	private int real_payment;
}