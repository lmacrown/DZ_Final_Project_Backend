package com.douzone.util;

import java.time.LocalDateTime;
import java.util.Map;

public class Utils {
	
	public static Map<String, Object> putPrimitive(Map<String, Object> result) {
		LocalDateTime time_stamp = LocalDateTime.now();
		result.put("status_code", true);
		result.put("time_stamp", time_stamp);
		return result;
	}
	
}
