package com.txyd.database.result;

public class ResultUtils {

	public static <T> SuccessBean<T> returnResult(T data) {
		SuccessBean<T> result = new SuccessBean<>();
		result.setRet(1);
		result.setData(data);
		return result;
	}

	public static ErrorBean returnResult(int code, String msg) {
		ErrorMsgBean errorMsg = new ErrorMsgBean();
		errorMsg.setCode(code);
		errorMsg.setMsg(msg);

		ErrorBean result = new ErrorBean();
		result.setRet(0);
		result.setError(errorMsg);

		return result;
	}

}
