package com.douzone.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarnerTaxVO {
	 private String isTuition;
	    private String deductionAmount;
	    private String isArtist;
	    private String insReduce;
	    private String taxId;
	    private String taxRate;
	    private String insRate;
	    private String totalPayment;
	    private String accrualYm;
	    private String paymentYm;
	    private String paymentDate;
	    private String calculated;
	    private String taxIncome;
	    private String taxLocal;
	    private String artistCost;
	    private String insCost;
	    private String taxTotal;
	    private String realPayment;
}