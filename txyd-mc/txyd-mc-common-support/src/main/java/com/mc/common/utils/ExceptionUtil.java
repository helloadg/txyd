package com.mc.common.utils;


import com.mc.common.exception.BaseException;
import com.mc.common.exception.BusynessException;
import com.mc.common.exception.DbException;
import com.mc.common.exception.ExceptionCode;
import com.mc.common.exception.InvalidParamException;
import com.mc.common.exception.NotResponseException;
import com.mc.common.exception.ResourceExistException;
import com.mc.common.exception.ResourceNotExistException;
import com.mc.common.exception.RpcException;
import com.mc.common.exception.SystemException;
import com.mc.common.exception.WebException;
import com.mc.common.exception.WithoutAuthorityException;
import com.mc.common.exception.WithoutLoginException;

/**
 * Created by Administrator on 2016/9/23.
 */
public class ExceptionUtil {
	
	/**
	 * 转换错误，返回异常
	 * 获取异常码
	 *
	 * @param ex
	 * @return
	 */
	public static BaseException parseException(Throwable ex) {
		if (isSelfDefinedException(ex)) {
			BaseException be = (BaseException) ex;
			return be;
		} else {
			return new SystemException("系统错误，请联系管理人员");
		}
		
	}
	
	/**
	 * 根据错误码，转换错误，返回异常
	 *
	 * @param errorCode
	 * @param message
	 * @return
	 */
	public static RuntimeException parseException(int errorCode, String message) {
		
		if (errorCode == ExceptionCode.busyness.code) {
			return new BusynessException(message);
		} else if (errorCode == ExceptionCode.invalidParam.code) {
			return new InvalidParamException(message);
		} else if (errorCode == ExceptionCode.notResponse.code) {
			return new NotResponseException(message);
		} else if (errorCode == ExceptionCode.resourceExist.code) {
			return new ResourceExistException(message);
		} else if (errorCode == ExceptionCode.resourceNotExist.code) {
			return new ResourceNotExistException(message);
		} else if (errorCode == ExceptionCode.withoutAuthority.code) {
			return new WithoutAuthorityException(message);
		} else if (errorCode == ExceptionCode.rpc.code) {
			return new RpcException(message);
		} else if (errorCode == ExceptionCode.web.code) {
			return new WebException(message);
		} else if (errorCode == ExceptionCode.withoutLogin.code) {
			return new WithoutLoginException(message);
		}else if (errorCode == ExceptionCode.db.code) {
			return new DbException(message);
		}
		
		
		return new SystemException(message);
	}
	
	/**
	 * 判断是否是自定义异常
	 *
	 * @param ex
	 * @return
	 */
	public static boolean isSelfDefinedException(Throwable ex) {
		
		try {
			if (ex instanceof BaseException) {
				return true;
			} else {
				return false;
			}
		} catch (Throwable throwable) {
			return false;
		}
	}
	
	public static void main(String[] args) {
		Class<?> clazz = BaseException.class;
		System.out.println(clazz);
		
	}
}
