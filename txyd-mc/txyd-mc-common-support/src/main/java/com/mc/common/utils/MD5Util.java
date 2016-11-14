package com.mc.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Util {
	public final static String MD5(String plaintext, String key) {
		MessageDigest messageDigest = null;
		try {
			plaintext += key;
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(plaintext.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}
	
	public final static String MD5ForSeaweedfs(String source) throws Exception {
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		
		byte[] btInput = source.getBytes("UTF-8");
		// 获得MD5摘要算法的 MessageDigest 对象
		MessageDigest mdInst = MessageDigest.getInstance("MD5");
		// 使用指定的字节更新摘要
		mdInst.update(btInput);
		// 获得密文
		byte[] ciphertext = mdInst.digest();
		// 把密文转换成十六进制的字符串形式
		int length=ciphertext.length;
		char chars[] = new char[length * 2];
		for (int i = 0; i < length; i++) {
			byte byte0 = ciphertext[i];
			chars[2*i] = hexDigits[byte0 >>> 4 & 0xf];
			chars[2*i+1] = hexDigits[byte0 & 0xf];
		}
		return new String(chars);
	}
	
	public static void main(String[] args) throws Exception {
		String  string= "dd";
		String str= str = MD5ForSeaweedfs(string);

		System.out.println(str);
		System.out.println(Byte.toString(new Integer(0xf).byteValue()));
		System.out.println(new BigInteger(new Integer(0xf).toString()).toString(2));
	}
}
