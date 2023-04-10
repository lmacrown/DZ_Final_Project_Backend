package com.douzone.entity.input;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarnerSearchVO {

    @NotBlank(message = "worker_id is required")
    private String worker_id;

    @NotNull(message = "search_value is required")
    private String search_value;

    @Min(value = 200000, message = "payment_ym must be a 6-digit positive number")
    @Max(value = 299999, message = "payment_ym must be a 6-digit positive number")
    private int payment_ym;
}