package com.txyd.database.exception;

/**
 * Exception
 *
 */
public class NotResponseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotResponseException(String msg) {
		super(msg);
	}

	public NotResponseException(String msg, Throwable t) {
		super(msg, t);
	}
}
