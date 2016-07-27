
package com.txyd.database.advice;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.txyd.database.exception.BusinessException;
import com.txyd.database.exception.InvalidParamException;
import com.txyd.database.exception.NotResponseException;
import com.txyd.database.exception.ResourceExistException;
import com.txyd.database.exception.ResourceNotExistException;
import com.txyd.database.exception.SystemException;
import com.txyd.database.exception.WithoutAuthorityException;
import com.txyd.database.result.ErrorBean;
import com.txyd.database.result.ErrorCodeInfo;
import com.txyd.database.result.ErrorMsgBean;


/**
 * Controller Advice
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {
	private Logger logger = Logger.getLogger(ExceptionHandlerAdvice.class);

	@ExceptionHandler(SystemException.class)
	@ResponseBody
	public ErrorBean handleSystem(HttpServletRequest req, Exception ex) {
		
		return getErrorBean(ErrorCodeInfo.SYSTEM_EXCEPTION, ex);
	}

	@ExceptionHandler(InvalidParamException.class)
	@ResponseBody
	public ErrorBean handleInvalidParam(HttpServletRequest req, Exception ex) {
		return getErrorBean(ErrorCodeInfo.INVALID_PARAM, ex);
	}

	@ExceptionHandler(ResourceExistException.class)
	@ResponseBody
	public ErrorBean handleResourceExist(HttpServletRequest req, Exception ex) {
		return getErrorBean(ErrorCodeInfo.RESOURCE_EXIST, ex);
	}

	@ExceptionHandler(ResourceNotExistException.class)
	@ResponseBody
	public ErrorBean handleResourceNotExist(HttpServletRequest req, Exception ex) {
		return getErrorBean(ErrorCodeInfo.RESOURCE_NOT_EXIST, ex);
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public ErrorBean handleBusinessException(HttpServletRequest req, BusinessException ex) {
		return getErrorBean(ErrorCodeInfo.BUSINESS_EXCEPTION, ex);
	}

	@ExceptionHandler(NotResponseException.class)
	@ResponseBody
	public ErrorBean notResponseException(HttpServletRequest req, BusinessException ex) {
		return getErrorBean(ErrorCodeInfo.NOT_RESPONSE_EXCEPTION, ex);
	}

	@ExceptionHandler(WithoutAuthorityException.class)
	@ResponseBody
	public ErrorBean withoutAuthorityException(HttpServletRequest req, BusinessException ex) {
		return getErrorBean(ErrorCodeInfo.WITHOUT_AUTHORITY_EXCEPTION, ex);
	}

	private  ErrorBean getErrorBean(ErrorCodeInfo codeInfo, Exception ex){
		ErrorMsgBean msgBean=new ErrorMsgBean();
		msgBean.setCode(new Integer(codeInfo.getCode()));
		msgBean.setMsg(ex.getMessage());
		logger.error("error:", ex);
		logger.debug("Exception：ErrorCode=[" + codeInfo.getCode() + "]  ErrorMessage=[" + ex.getMessage() + "]");
		
		ErrorBean errorBean=new ErrorBean();
		errorBean.setError(msgBean);
		return errorBean;
		
	}

}
