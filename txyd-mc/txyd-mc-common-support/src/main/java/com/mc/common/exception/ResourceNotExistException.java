package com.mc.common.exception;



/**
 * Exception
 * 描述：资源不存在
 * @author Administrator
 *
 */
public class ResourceNotExistException extends RuntimeException implements BaseException {
	private static final long serialVersionUID = 1L;
	public ResourceNotExistException(String msg) {
		super(msg);
	}
	public ResourceNotExistException(String msg, Throwable t) {
		super(msg, t);
	}
	
	
	public int getErrorCode() {
		return ExceptionCode.resourceNotExist.code;
	}
}
