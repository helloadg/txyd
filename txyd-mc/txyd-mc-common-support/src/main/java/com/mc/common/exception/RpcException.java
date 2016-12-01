package com.mc.common.exception;


/**
 * Exception
 * 描述：远程调用异常
 *
 * @author Administrator
 */
public class RpcException extends RuntimeException implements BaseException {
	private static final long serialVersionUID = 1L;
	
	public RpcException(String msg) {
		super(msg);
	}
	
	public RpcException(String msg, Throwable t) {
		super(msg, t);
	}
	
	
	public int getErrorCode() {
		return ExceptionCode.rpc.code;
	}
}
