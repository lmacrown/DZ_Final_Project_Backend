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
public class EarnerInsertVO {

    @NotBlank(message = "worker_id is required")
    @Size(min = 6, max = 13, message = "worker_id must be between 6 and 13 characters")
    private String worker_id;

    @NotBlank(message = "earner_code is required")
    @Pattern(regexp = "^\\d{6}$", message = "earner_code must be a 6-digit number")
    private String earner_code;
    
    @NotBlank(message = "earner_name is required")
    private String earner_name;
    
    @NotBlank(message = "div_code is required")
    private String div_code;
    
    @NotBlank(message = "div_name is required")
    private String div_name;
    
    @NotBlank(message = "div_type is required")
    private String div_type;
    
    private String personal_no;
    
    private String is_native;
    
    private int is_default;
    
    private String div_type;
}