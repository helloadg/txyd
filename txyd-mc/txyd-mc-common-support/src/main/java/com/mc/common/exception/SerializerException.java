package com.mc.common.exception;

/**
 * Exception
 * 描述：序列化异常
 * @author Administrator
 *
 */
public class SerializerException extends RuntimeException implements BaseException {
	private static final long serialVersionUID = 1L;

	public SerializerException(String msg) {
		super(msg);
	}

	public SerializerException(String msg, Throwable t) {
		super(msg, t);
	}
	
	
	public int getErrorCode() {
		return ExceptionCode.serializer.code;
	}
	
}
