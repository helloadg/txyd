package com.txyd.database.exception;

/**
 * Exception
 *
 */
public class WithoutAuthorityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public WithoutAuthorityException(String msg) {
		super(msg);
	}

	public WithoutAuthorityException(String msg, Throwable t) {
		super(msg, t);
	}
}
