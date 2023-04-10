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
public class ListOccupationVO {

    @NotBlank(message = "earner_type is required")
    @Pattern(regexp = "^(일반|단기)$", message = "earner_type is valid")
    private String earner_type;

    private String search_value;
}