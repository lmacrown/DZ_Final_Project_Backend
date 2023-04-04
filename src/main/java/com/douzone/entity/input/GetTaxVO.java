package com.douzone.entity.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTaxVO {

    //@NotBlank(message = "earner_code is required")
    @Pattern(regexp = "^\\d{6}$", message = "earner_code must be a 6-digit number")
    private String earner_code;

    @NotBlank(message = "worker_id is required")
    private String worker_id;

//    @Min(value = 200000, message = "payment_ym must be a 6-digit positive number")
//    @Max(value = 299999, message = "payment_ym must be a 6-digit positive number")
    private int payment_ym;
    
    private String[] select_dates;
    
    private String select_date;
    
    //@Pattern(regexp = "^\\d{6}$", message = "earner_code must be a 6-digit number")
    private int accrual_ym;
    // Getters and setters
}