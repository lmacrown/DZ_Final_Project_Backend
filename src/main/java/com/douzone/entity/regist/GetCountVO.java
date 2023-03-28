package com.douzone.entity.regist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCountVO {

    @NotBlank(message = "worker_id is required")
    private String worker_id;

    @Min(value = 1, message = "code_count must be greater than or equal to 1")
    private int code_count;
}