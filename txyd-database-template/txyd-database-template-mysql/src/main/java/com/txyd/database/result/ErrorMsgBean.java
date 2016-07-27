package com.txyd.database.result;

/**
 * 统一返回结果类
 * 
 * @author Administrator
 *
 * @param <T>
 */

public class ErrorMsgBean {
	private Integer code;
	private String msg;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
