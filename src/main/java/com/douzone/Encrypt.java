package com.douzone;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Encrypt {

//	public static void main(String[] args) {
//		Encrypt en = new Encrypt();
//		
//		String pwd = "passwd";
//		String salt = en.getSalt();
//		String res = en.getEncrypt(pwd, salt);
//	}

	public String getSalt() {
		SecureRandom  r = new SecureRandom();
		
		byte[] salt = new byte[20];
		r.nextBytes(salt);
		StringBuffer sb = new StringBuffer();
		
		for(byte b : salt) {
			sb.append(String.format("%02x", b));
		};
		
		return sb.toString();
	}
	
	public String getEncrypt(String pwd, String salt) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			md.update((pwd+salt).getBytes());
			byte[] pwdsalt = md.digest();
			StringBuffer sb = new StringBuffer();
			
			for (byte b : pwdsalt) {
				sb.append(String.format("%02x", b));
			}
			
			result=sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
}