package com.douzone.entity.regist;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarnerDeleteVO {

    @NotBlank(message = "worker_id is required")
    private String worker_id;
    
    
    @NotEmpty(message = "earner_codes must not be empty")
    @NotNull(message = "earner_codes is required")
    private List<@NotBlank @Pattern(regexp = "^\\d{6}$", message = "earner_code must be a 6-digit number") String> earner_codes;
}