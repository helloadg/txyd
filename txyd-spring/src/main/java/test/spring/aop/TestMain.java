package test.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
	private static ApplicationContext ctx;

    public static void main(String[] args) {
    	IHello helloService = new HelloService();
        helloService.sayHello();
        helloService.sayHello2();
        System.out.println("*************************");
        ctx = new ClassPathXmlApplicationContext("spring-aop-schema.xml");
        
        helloService = ctx.getBean("helloService",IHello.class);
        
        helloService.sayHello();
        helloService.sayHello2();
    }
}
