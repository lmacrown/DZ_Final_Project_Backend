package com.douzone.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponseVO {
	private boolean status_code;
	private LocalDateTime timestamp;
	private int status;
	private String message;
	private String details;
}
