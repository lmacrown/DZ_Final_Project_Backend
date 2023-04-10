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
public class GetOccupationVO {
    @NotBlank(message = "earner_type is required")
    @Pattern(regexp = "^(일반|단기)$", message = "earner_type is valid")
    private String earner_type;

    @Pattern(regexp = "^\\d{3}$", message = "occupation_code must be a 3-digit number")
    private String occupation_code;
}
