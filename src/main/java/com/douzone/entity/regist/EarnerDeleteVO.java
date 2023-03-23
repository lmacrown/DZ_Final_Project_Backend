package com.douzone.entity.regist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarnerDeleteVO {

    @NotBlank(message = "worker_id is required")
    private String worker_id;

    @NotNull(message = "earner_codes is required")
    private List<String> earner_codes;
}