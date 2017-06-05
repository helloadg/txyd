package txyd.util;


import txyd.common.ServiceResult;
import txyd.exception.BaseException;

/**
 * Created by Administrator on 2016/9/14.
 */
public class ServiceResultUtil {
	
	public static <T> ServiceResult<T> success(T body) {
		ServiceResult<T> serviceResult = new ServiceResult<>();
		serviceResult.setSuccess(true);
		serviceResult.setBody(body);
		return serviceResult;
	}
	
	public static ServiceResult failure(Integer errorCode, String message) {
		ServiceResult serviceResult = new ServiceResult<>();
		serviceResult.setSuccess(false);
		serviceResult.setErrorCode(errorCode);
		serviceResult.setMessage(message);
		return serviceResult;
	}
	
	public static ServiceResult<RuntimeException> failure(Throwable ex) {
		BaseException be = ExceptionUtil.parseException(ex);
		
		ServiceResult<RuntimeException> sr = new ServiceResult<>();
		sr.setSuccess(false);
		sr.setErrorCode(be.getErrorCode());
		sr.setMessage(be.getMessage());
		
		return sr;
	}
	
	
}
