package com.mc.common.exception;

/**
 * Exception
 * 描述：非法参数
 *
 * @author Administrator
 */
public class InvalidParamException extends RuntimeException implements BaseException {
	private static final long serialVersionUID = 1L;
	
	public InvalidParamException(String msg) {
		super(msg);
	}
	
	public InvalidParamException(String msg, Throwable t) {
		super(msg, t);
	}
	
	
	public int getErrorCode() {
		return ExceptionCode.invalidParam.code;
	}
	
	
}
