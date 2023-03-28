package com.douzone.entity.input;

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

    // Getters and setters
}