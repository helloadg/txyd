package test.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class HelloAroundAdvice implements MethodInterceptor {

    public Object invoke(MethodInvocation arg0) throws Throwable {

        System.out.println("++++++before advice");
        arg0.proceed();
        System.out.println("++++++after advice");
        
        return null;
    }

}