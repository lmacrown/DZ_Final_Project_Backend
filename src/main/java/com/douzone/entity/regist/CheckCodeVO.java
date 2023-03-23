package com.douzone.entity.regist;

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
public class CheckCodeVO {

    @NotBlank(message = "custom_code is required")
    @Pattern(regexp = "^\\d{6}$", message = "custom_code must be a 6-digit number")
    private String custom_code;

    @NotBlank(message = "worker_id is required")
    private String worker_id;
}