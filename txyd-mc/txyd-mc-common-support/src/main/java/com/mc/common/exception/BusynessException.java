package com.mc.common.exception;


/**
 * Exception
 * 描述：
 *
 * @author Administrator
 */
public class BusynessException extends RuntimeException implements BaseException {
	private static final long serialVersionUID = 1L;
	
	public BusynessException(String msg) {
		super(msg);
	}
	
	public BusynessException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public int getErrorCode() {
		return  ExceptionCode.busyness.code;
	}
	

	
}
