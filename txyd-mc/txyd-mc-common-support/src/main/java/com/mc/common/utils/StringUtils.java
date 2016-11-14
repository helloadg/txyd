/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mc.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Collection;


/**
 * Converts String to and from bytes using the encodings required by the Java specification. These encodings are specified in <a
 * href="http://java.sun.com/j2se/1.4.2/docs/api/java/nio/charset/Charset.html">Standard charsets</a>
 *
 * @author <a href="mailto:ggregory@seagullsw.com">Gary Gregory</a>
 * @version $Id: StringUtils.java 801391 2009-08-05 19:55:54Z ggregory $
 * @see CharEncoding
 * @see <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/nio/charset/Charset.html">Standard charsets</a>
 * @since 1.4
 */
public class StringUtils {
	
	/**
	 * Encodes the given string into a sequence of bytes using the ISO-8859-1 charset, storing the result into a new
	 * byte array.
	 *
	 * @param string the String to encode
	 * @return encoded bytes
	 * @throws IllegalStateException Thrown when the charset is missing, which should be never according the the Java specification.
	 * @see <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/nio/charset/Charset.html">Standard charsets</a>
	 * @see #getBytesUnchecked(String, String)
	 */
	public static byte[] getBytesIso8859_1(String string) {
		return StringUtils.getBytesUnchecked(string, CharEncoding.ISO_8859_1);
	}
	
	/**
	 * Encodes the given string into a sequence of bytes using the US-ASCII charset, storing the result into a new byte
	 * array.
	 *
	 * @param string the String to encode
	 * @return encoded bytes
	 * @throws IllegalStateException Thrown when the charset is missing, which should be never according the the Java specification.
	 * @see <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/nio/charset/Charset.html">Standard charsets</a>
	 * @see #getBytesUnchecked(String, String)
	 */
	public static byte[] getBytesUsAscii(String string) {
		return StringUtils.getBytesUnchecked(string, CharEncoding.US_ASCII);
	}
	
	/**
	 * Encodes the given string into a sequence of bytes using the UTF-16 charset, storing the result into a new byte
	 * array.
	 *
	 * @param string the String to encode
	 * @return encoded bytes
	 * @throws IllegalStateException Thrown when the charset is missing, which should be never according the the Java specification.
	 * @see <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/nio/charset/Charset.html">Standard charsets</a>
	 * @see #getBytesUnchecked(String, String)
	 */
	public static byte[] getBytesUtf16(String string) {
		return StringUtils.getBytesUnchecked(string, CharEncoding.UTF_16);
	}
	
	/**
	 * Encodes the given string into a sequence of bytes using the UTF-16BE charset, storing the result into a new byte
	 * array.
	 *
	 * @param string the String to encode
	 * @return encoded bytes
	 * @throws IllegalStateException Thrown when the charset is missing, which should be never according the the Java specification.
	 * @see <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/nio/charset/Charset.html">Standard charsets</a>
	 * @see #getBytesUnchecked(String, String)
	 */
	public static byte[] getBytesUtf16Be(String string) {
		return StringUtils.getBytesUnchecked(string, CharEncoding.UTF_16BE);
	}
	
	/**
	 * Encodes the given string into a sequence of bytes using the UTF-16LE charset, storing the result into a new byte
	 * array.
	 *
	 * @param string the String to encode
	 * @return encoded bytes
	 * @throws IllegalStateException Thrown when the charset is missing, which should be never according the the Java specification.
	 * @see <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/nio/charset/Charset.html">Standard charsets</a>
	 * @see #getBytesUnchecked(String, String)
	 */
	public static byte[] getBytesUtf16Le(String string) {
		return StringUtils.getBytesUnchecked(string, CharEncoding.UTF_16LE);
	}
	
	/**
	 * Encodes the given string into a sequence of bytes using the UTF-8 charset, storing the result into a new byte
	 * array.
	 *
	 * @param string the String to encode
	 * @return encoded bytes
	 * @throws IllegalStateException Thrown when the charset is missing, which should be never according the the Java specification.
	 * @see <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/nio/charset/Charset.html">Standard charsets</a>
	 * @see #getBytesUnchecked(String, String)
	 */
	public static byte[] getBytesUtf8(String string) {
		return StringUtils.getBytesUnchecked(string, CharEncoding.UTF_8);
	}
	
	/**
	 * Encodes the given string into a sequence of bytes using the named charset, storing the result into a new byte
	 * array.
	 * <p>
	 * This method catches {@link UnsupportedEncodingException} and rethrows it as {@link IllegalStateException}, which
	 * should never happen for a required charset name. Use this method when the encoding is required to be in the JRE.
	 * </p>
	 *
	 * @param string      the String to encode
	 * @param charsetName The name of a required {@link java.nio.charset.Charset}
	 * @return encoded bytes
	 * @throws IllegalStateException Thrown when a {@link UnsupportedEncodingException} is caught, which should never happen for a
	 *                               required charset name.
	 * @see CharEncoding
	 * @see String#getBytes(String)
	 */
	public static byte[] getBytesUnchecked(String string, String charsetName) {
		if (string == null) {
			return null;
		}
		try {
			return string.getBytes(charsetName);
		} catch (UnsupportedEncodingException e) {
			throw StringUtils.newIllegalStateException(charsetName, e);
		}
	}
	
	private static IllegalStateException newIllegalStateException(String charsetName, UnsupportedEncodingException e) {
		return new IllegalStateException(charsetName + ": " + e);
	}
	
	/**
	 * Constructs a new <code>String</code> by decoding the specified array of bytes using the given charset.
	 * <p>
	 * This method catches {@link UnsupportedEncodingException} and re-throws it as {@link IllegalStateException}, which
	 * should never happen for a required charset name. Use this method when the encoding is required to be in the JRE.
	 * </p>
	 *
	 * @param bytes       The bytes to be decoded into characters
	 * @param charsetName The name of a required {@link java.nio.charset.Charset}
	 * @return A new <code>String</code> decoded from the specified array of bytes using the given charset.
	 * @throws IllegalStateException Thrown when a {@link UnsupportedEncodingException} is caught, which should never happen for a
	 *                               required charset name.
	 * @see CharEncoding
	 * @see String#String(byte[], String)
	 */
	public static String newString(byte[] bytes, String charsetName) {
		if (bytes == null) {
			return null;
		}
		try {
			return new String(bytes, charsetName);
		} catch (UnsupportedEncodingException e) {
			throw StringUtils.newIllegalStateException(charsetName, e);
		}
	}
	
	/**
	 * Constructs a new <code>String</code> by decoding the specified array of bytes using the ISO-8859-1 charset.
	 *
	 * @param bytes The bytes to be decoded into characters
	 * @return A new <code>String</code> decoded from the specified array of bytes using the given charset.
	 * @throws IllegalStateException Thrown when a {@link UnsupportedEncodingException} is caught, which should never happen since the
	 *                               charset is required.
	 */
	public static String newStringIso8859_1(byte[] bytes) {
		return StringUtils.newString(bytes, CharEncoding.ISO_8859_1);
	}
	
	/**
	 * Constructs a new <code>String</code> by decoding the specified array of bytes using the US-ASCII charset.
	 *
	 * @param bytes The bytes to be decoded into characters
	 * @return A new <code>String</code> decoded from the specified array of bytes using the given charset.
	 * @throws IllegalStateException Thrown when a {@link UnsupportedEncodingException} is caught, which should never happen since the
	 *                               charset is required.
	 */
	public static String newStringUsAscii(byte[] bytes) {
		return StringUtils.newString(bytes, CharEncoding.US_ASCII);
	}
	
	/**
	 * Constructs a new <code>String</code> by decoding the specified array of bytes using the UTF-16 charset.
	 *
	 * @param bytes The bytes to be decoded into characters
	 * @return A new <code>String</code> decoded from the specified array of bytes using the given charset.
	 * @throws IllegalStateException Thrown when a {@link UnsupportedEncodingException} is caught, which should never happen since the
	 *                               charset is required.
	 */
	public static String newStringUtf16(byte[] bytes) {
		return StringUtils.newString(bytes, CharEncoding.UTF_16);
	}
	
	/**
	 * Constructs a new <code>String</code> by decoding the specified array of bytes using the UTF-16BE charset.
	 *
	 * @param bytes The bytes to be decoded into characters
	 * @return A new <code>String</code> decoded from the specified array of bytes using the given charset.
	 * @throws IllegalStateException Thrown when a {@link UnsupportedEncodingException} is caught, which should never happen since the
	 *                               charset is required.
	 */
	public static String newStringUtf16Be(byte[] bytes) {
		return StringUtils.newString(bytes, CharEncoding.UTF_16BE);
	}
	
	/**
	 * Constructs a new <code>String</code> by decoding the specified array of bytes using the UTF-16LE charset.
	 *
	 * @param bytes The bytes to be decoded into characters
	 * @return A new <code>String</code> decoded from the specified array of bytes using the given charset.
	 * @throws IllegalStateException Thrown when a {@link UnsupportedEncodingException} is caught, which should never happen since the
	 *                               charset is required.
	 */
	public static String newStringUtf16Le(byte[] bytes) {
		return StringUtils.newString(bytes, CharEncoding.UTF_16LE);
	}
	
	/**
	 * Constructs a new <code>String</code> by decoding the specified array of bytes using the UTF-8 charset.
	 *
	 * @param bytes The bytes to be decoded into characters
	 * @return A new <code>String</code> decoded from the specified array of bytes using the given charset.
	 * @throws IllegalStateException Thrown when a {@link UnsupportedEncodingException} is caught, which should never happen since the
	 *                               charset is required.
	 */
	public static String newStringUtf8(byte[] bytes) {
		return StringUtils.newString(bytes, CharEncoding.UTF_8);
	}
	
	
	/**
	 * 判断字符串是否为空
	 *
	 * @param parms
	 * @return
	 */
	public static boolean isBlankOrEmpty(String parms) {
		return StringUtils.isBlank(parms) || StringUtils.isEmpty(parms);
	}
	
	/**
	 * 判断是否是blank
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 *
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 判断是否是empty
	 * StringUtils.isEmpty(null)      = true
	 * StringUtils.isEmpty("")        = true
	 * StringUtils.isEmpty(" ")       = false
	 * StringUtils.isEmpty("bob")     = false
	 * StringUtils.isEmpty("  bob  ") = false
	 *
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	/**
	 * 字符串转码
	 *
	 * @param str
	 * @return
	 */
	public static String encodeStr(String str) {
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
//    /**
//     * 判断字符串是否为数字
//     * @param parms
//     * @return
//     */
//    public static boolean isInteger(String str) {
//        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
//        return pattern.matcher(str).matches();
//    }
	
	/**
	 * 判断字符串是否为数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		try {
			Integer.valueOf(str.trim());
		} catch (Throwable throwable) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断字符串是否为数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean isLong(String str) {
		try {
			Long.valueOf(str.trim());
		} catch (Throwable throwable) {
			return false;
		}
		return true;
	}
	
	/**
	 * 将数字转为Integer
	 *
	 * @param str
	 * @return
	 */
	public static Integer getInteger(String str) {
		Integer result = null;
		try {
			result = Integer.valueOf(str.trim());
		} catch (Throwable throwable) {
		}
		return result;
	}
	
	/**
	 * 将数字转为Long
	 *
	 * @param str
	 * @return
	 */
	public static Long getLong(String str) {
		Long result = null;
		try {
			result = Long.valueOf(str.trim());
		} catch (Throwable throwable) {
		}
		return result;
	}
	
	/**
	 * 使用分隔符分割集合的元素
	 *
	 * @param cs
	 * @param separator
	 * @return
	 */
	public static String getStrByCollectionAndSeparator(final Collection<?> cs, final String separator) {
		if (cs == null || cs.size() == 0) {
			return "";
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (Object item : cs) {
			stringBuilder.append(item.toString() + separator);
		}
		return stringBuilder.substring(0, stringBuilder.lastIndexOf(separator)).toString();
	}
	
	/**
	 * 使用分隔符分割集合的元素
	 *
	 * @param cs
	 * @param separator
	 * @return
	 */
	public static <T> String getStrByArrayAndSeparator(T[] cs, final String separator) {
		if (cs == null || cs.length == 0) {
			return "";
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (Object item : cs) {
			stringBuilder.append(item.toString() + separator);
		}
		return stringBuilder.substring(0, stringBuilder.lastIndexOf(separator)).toString();
	}
	
	/**
	 * 获取字符串的长度，如果有中文，则每个中文字符计为2位
	 *
	 * @param value 指定的字符串
	 * @return 字符串的长度
	 */
	public static int length(String value) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
	    /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
			String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
			if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
				valueLength += 2;
			} else {
                /* 其他字符长度为1 */
				valueLength += 1;
			}
		}
		return valueLength;
	}
	
	/**
	 * 如果是null，则返回空字符串
	 * 否者返回str的trim之后的结果
	 * @param str
	 * @return
	 */
	public static String nullToEmptyAndTrim(String str) {
		return str == null ? "" : str.trim();
	}
	
	
	public static void main(String[] args) {
		System.out.println(5 % 5);
		System.out.println(isLong("1111111111111111111111"));
		StringBuilder sb = new StringBuilder("1_2_3_4_5_");
		System.out.println(sb.substring(0, sb.lastIndexOf("_")));
	}
}
