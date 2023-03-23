package com.douzone.entity.regist;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetEarnerVO {

    @NotBlank(message = "worker_id is required")
    @Size(min = 6, max = 13, message = "worker_id must be between 6 and 13 characters")
    private String worker_id;

    @NotBlank(message = "earner_code is required")
    @Pattern(regexp = "^\\d{6}$", message = "earner_code must be a 6-digit number")
    private String earner_code;

}