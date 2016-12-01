package com.mc.common.exception;


/**
 * Exception
 * 描述：系统参数
 *
 * @author Administrator
 */
public class SystemException extends RuntimeException implements BaseException {
	private static final long serialVersionUID = 1L;
	
	public SystemException(String msg) {
		super(msg);
	}
	
	public SystemException(String msg, Throwable t) {
		super(msg, t);
	}
	
	
	public int getErrorCode() {
		return ExceptionCode.system.code;
	}
}
