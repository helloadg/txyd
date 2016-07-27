package com.txyd.database.exception;

/**
 * Exception
 * 
 * @author panzhiqi
 *
 */
public class InvalidParamException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidParamException(String msg) {
		super(msg);
	}

	public InvalidParamException(String msg, Throwable t) {
		super(msg, t);
	}
}
