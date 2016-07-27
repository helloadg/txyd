package com.txyd.database.result;

/**
 * 统一返回结果类
 * 
 * @author Administrator
 */

public class ErrorBean {
	private Integer ret = 0;
	private ErrorMsgBean error;

	public Integer getRet() {
		return ret;
	}

	public void setRet(Integer ret) {
		this.ret = ret;
	}

	public ErrorMsgBean getError() {
		return error;
	}

	public void setError(ErrorMsgBean error) {
		this.error = error;
	}

}
