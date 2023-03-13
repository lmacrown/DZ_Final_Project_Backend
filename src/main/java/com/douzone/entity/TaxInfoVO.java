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
	private int tuition_amount;
	private int real_payment;
	

	
	private static final float TAX_RATE_PERCENT = 0.01f;
	private static final float ARTIST_COST_RATE = 0.25f;
	private static final float LOCAL_TAX_RATE = 0.1f;
	private static final int MAX_INS_COST = 220570;

	public void calculate_tax() {
	    float taxRate = tax_rate * TAX_RATE_PERCENT; // 세율

	    // 고용 보험료, 예술인 경비 계산
	    artist_cost = (int) (total_payment * ARTIST_COST_RATE);
	    ins_cost = (int) ((total_payment - artist_cost) * ins_rate * ins_reduce);

	    if (ins_cost > MAX_INS_COST) {
	        ins_cost = MAX_INS_COST;
	    }

	    // 학자금 
	    tuition_amount = deduction_amount;
	    // 소득세 계산
	    tax_income = (int)(total_payment * taxRate);
	    // 지방소득세 계산
	    tax_local = (int)(tax_income * LOCAL_TAX_RATE);
	    // 세액계 계산
	    tax_total = tax_income + tax_local + tuition_amount;
	    // 차인지급액 계산
	    real_payment= (total_payment - (tax_total + ins_cost));
	    // 계산 값 백업
	    calculated = true;
	}

}