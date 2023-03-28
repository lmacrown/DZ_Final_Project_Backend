package com.douzone.entity.input;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaxDateVO {

	@Min(value = 1, message = "payment_date must be 1-31 positive number")
    @Max(value = 31, message = "payment_ym must be 1-31 positive number")
    private int payment_date;

	@Min(value = 200000, message = "accrual_ym must be a 6-digit positive number")
    @Max(value = 299999, message = "accrual_ym must be a 6-digit positive number")
    private int accrual_ym;

    @NotNull(message = "tax_id is required")
    private Integer tax_id;

    // Getters and setters
}