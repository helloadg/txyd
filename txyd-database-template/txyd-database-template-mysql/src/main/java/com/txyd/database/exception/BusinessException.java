package com.txyd.database.exception;

public class BusinessException extends RuntimeException {

	public static final int NOT_ENOUGH = 1130;
	private static final long serialVersionUID = 1L;
	private int errorCode;

	public BusinessException(int errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;

	}

	public BusinessException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return this.errorCode;
	}

	public BusinessException(int errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

}
