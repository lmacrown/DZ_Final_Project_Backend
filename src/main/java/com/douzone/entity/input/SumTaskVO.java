package com.douzone.entity.input;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SumTaskVO {

    @NotBlank(message = "worker_id is required")
    private String worker_id;

    @Min(value = 200000, message = "payment_ym must be a 6-digit positive number")
    @Max(value = 299999, message = "payment_ym must be a 6-digit positive number")
    private int payment_ym; 
}