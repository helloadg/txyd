package test.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
	private static final ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-aop-schema.xml");

    public static void main(String[] args) {
        {
            IHello helloService = new HelloService();
            helloService.sayHello();
            System.out.println("*************************");
        }
        {
            IHello helloService = ctx.getBean("helloService",IHello.class);
    
            helloService.sayHello();
        }
        
    }
}
