package test.spring.component.scan1.auto;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
	private static ApplicationContext ctx;

    public static void main(String[] args) {
        ctx = new ClassPathXmlApplicationContext("spring-component-scan-schema.xml");
    }
}
