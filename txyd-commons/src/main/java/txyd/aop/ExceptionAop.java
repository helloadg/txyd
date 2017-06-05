package txyd.aop;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import txyd.common.ServiceResult;
import txyd.util.JsonUtil;
import txyd.util.ServiceResultUtil;
import txyd.util.TimeUtil;

/**
 *   AOP的AfterThrowing处理虽然可以对目标方法的异常进行处理，但这种处理与直接使用catch捕捉不同，catch捕捉意味着完全处理该异常，如果catch块中没有重新抛出新的异常，则该方法可能正常结束；而AfterThrowing处理虽然处理了该异常，但它不能完全处理异常，该异常依然会传播到上一级调用者，即JVM。
 */
//@Component
//@Aspect
public class ExceptionAop {
	private static final Logger logger = Logger.getLogger(ExceptionAop.class);
//	
//	
//	@Pointcut(" (* com.mc.lottery.*.service.impl..*(..))")
//	public void aspect(){	}
//	
//	@AfterThrowing(pointcut="aspect()", throwing="ex")
//	public void afterThrow(JoinPoint joinPoint, RuntimeException ex){
//		
//	}
	
	
	public ServiceResult<RuntimeException> afterThrow(JoinPoint joinPoint, Throwable ex){
		Object[] arguments =joinPoint.getArgs();
		String methodName=joinPoint.getSignature().getName();
		String className=joinPoint.getSignature().getDeclaringTypeName();

		String logError="方法："+className+"."+methodName+"；入参"+ JsonUtil.toJson(arguments)+";错误信息："+ex.getMessage();
		logger.error(logError,ex);
		

		return ServiceResultUtil.failure(ex);


	}
	public ServiceResult<?> around(ProceedingJoinPoint joinPoint){
		try{
//			long startTime= TimeUtil.getMillis();
			Object data=joinPoint.proceed();
			ServiceResult<Object>  sr= new ServiceResult<>();
			if(data instanceof ServiceResult){
				sr.setSuccess(((ServiceResult) (data)).getSuccess());
				sr.setMessage(((ServiceResult) (data)).getMessage());
				sr.setBody(((ServiceResult) (data)).getBody());
				sr.setErrorCode(((ServiceResult)(data)).getErrorCode());
			}else {
				sr.setSuccess(true);
				sr.setBody(data);
				sr.setErrorCode(0);
			}
			long endTime= TimeUtil.getMillis();
//			System.out.println("耗时："+(endTime-startTime));
			return sr;
		}catch (Throwable ex){
			return afterThrow(joinPoint,ex);
		}

	}
}
