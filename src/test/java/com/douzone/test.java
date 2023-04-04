package com.douzone;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class test {
	Map<String, Object> result = new HashMap<String, Object>();
	@Test
	public void AES() throws Exception {
	   Aes aes = new Aes("1234567");
	   String enc = aes.encrypt("1");
	   String dec = aes.decrypt(enc);
	   System.out.println(enc);
	   System.out.println(dec);
	}
	
}
