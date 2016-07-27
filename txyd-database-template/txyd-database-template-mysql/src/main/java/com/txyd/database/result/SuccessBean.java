package com.txyd.database.result;

/**
 * 统一返回结果类
 * 
 * @author Administrator
 *
 * @param <T>
 */

public class SuccessBean<T> {
	private Integer ret = 1;
	private T data;

	public Integer getRet() {
		return ret;
	}

	public void setRet(Integer ret) {
		this.ret = ret;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public SuccessBean() {
		super();
	}

	public SuccessBean(Integer ret, T data) {
		super();
		this.ret = ret;
		this.data = data;
	}

}
